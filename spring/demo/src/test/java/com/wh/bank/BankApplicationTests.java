package com.wh.bank;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.Account;
import com.wh.bank.entity.Employee;
import com.wh.bank.mapper.AccountMapper;
import com.wh.bank.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class BankApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Test
    public void test01() {
        Employee employee=new Employee();
        employee.setEmpName("熊文龙");
        employee.setGender("男");
        employee.setSalary(new BigDecimal(8340));
        employee.setJobTitle("我儿子");
        employeeMapper.insert(employee);

    }
    @Test
    public void test02(){
        Employee employee=new Employee();
        employee.setEmpId(5);
        employee.setEmpName("张伟航");
        employee.setGender("男");
        employee.setSalary(new BigDecimal(8000));
        employee.setJobTitle("大大大天才");
        employeeMapper.updateById(employee);
    }
    @Test
    public void test03(){
        Employee employee =employeeMapper.selectById(4);
        System.out.println(employee);
    }
    @Test
    public void test04(){
        List<Employee> employees=employeeMapper.selectList(null);
        for(Employee employee :employees){
            System.out.println(employee);
        }
    }
    @Test
    public void test05(){
        employeeMapper.deleteById(3);
    }
    /*
    员工表分页查询

     */
    @Test
    public void test06(){
        int pageSize=2;
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("gender","男");
        queryWrapper.orderByDesc("emp_id");

        Page<Employee> p= new Page<Employee>(1,pageSize);
        IPage<Employee> pe=employeeMapper.selectPage(p,null);
        System.out.println("总页数:"+pe.getPages());
        System.out.println("总记录条数:"+pe.getTotal());
        System.out.println("当前页:"+pe.getCurrent());
        System.out.println("一页显示多少条记录:"+pe.getSize());
        for(Employee employee:pe.getRecords()){
            System.out.println(employee);
        }


    }
    /*
    查询姓周的男性员工
    selent *from employee where emp_name like %周% and gender="男"
     */
    @Test
    public void test07(){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();

        queryWrapper.like("emp_name","张");
        queryWrapper.eq("gender","男");
        queryWrapper.gt("salary",6000);
        List<Employee> employees=employeeMapper.selectList(queryWrapper);
        for(Employee employee: employees){
            System.out.println(employee);
        }

    }
    @Test
    public void test08(){
       int pageSize = 1;
       QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
       Page<Account> p=new Page<Account>(1,pageSize);
       IPage<Account> pe=accountMapper.selectPage(p,null);
       for(Account account:pe.getRecords()){
           System.out.println(account); 
       }
    }



}
