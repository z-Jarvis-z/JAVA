package com.wh.bank.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wh.bank.entity.Employee;
import com.wh.bank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/emps/{page}")
    public IPage<Employee>pageEmployee(@PathVariable("page")Integer page
                                        ,@RequestParam(value="name",required = false,defaultValue = "")String name){

        IPage<Employee> pe=employeeService.pageEmployee(page,name);
        return pe;
    }

}
