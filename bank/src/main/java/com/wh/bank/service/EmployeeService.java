package com.wh.bank.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.Employee;
import com.wh.bank.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    public IPage<Employee> pageEmployee(int currPage,String name){

        QueryWrapper<Employee> queryWrapper=new QueryWrapper<Employee>();
        if(!"".equals(name)){
            queryWrapper.like("emp_name",name);
        }
        Page<Employee> p= new Page<>(currPage,4);
        IPage<Employee> pe=employeeMapper.selectPage(p,queryWrapper);
        return pe;
    }
}
