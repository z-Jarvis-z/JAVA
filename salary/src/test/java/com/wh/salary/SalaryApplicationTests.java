package com.wh.salary;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.salary.entity.Employee;
import com.wh.salary.mapper.EmployeeMapper;

@SpringBootTest
class SalaryApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void test01() {
        Employee employee=new Employee();
        employee.setEmpId(3);
        employee.setEmpName("冰某");
        employee.setGender("女");
        employee.setSalary(new BigDecimal(8000));
        employee.setJobTitle("歌手");
        employeeMapper.insert(employee);

    }
    @Test
    public void test02(){
        Employee employee=new Employee();
        employee.setEmpId(2);
        employee.setEmpName("郑某");
        employee.setGender("男");
        employee.setSalary(new BigDecimal(8000));
        employee.setJobTitle("员工二号");
        employeeMapper.updateById(employee);
    }
    @Test
    public void test03(){
        Employee employee =employeeMapper.selectById(1);
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
        employeeMapper.deleteById(7);
    }
    /*
    员工表分页查询

     */
    @Test
    public void test06(){
        int pageSize=4;
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

}
