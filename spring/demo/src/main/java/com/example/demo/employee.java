package com.example.demo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("employee")
public class employee implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer empId;
 
    private String empName;

    private String gender;

    private BigDecimal salary;
    
    private String jobTitle;

}
