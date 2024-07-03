package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {

	@Autowired//注入
	private empolyeemapper m_Empolyeemapper;

	@Test
	void test01() {
		employee new_Employee =new employee();
		new_Employee.setEmpName("测试");
		new_Employee.setSalary(new BigDecimal(100000));
		new_Employee.setGender("女");
		new_Employee.setJobTitle("高级程序架构师");
		m_Empolyeemapper.insert(new_Employee);
	}

	@Test
	void test02() {
		employee new_Employee =new employee();
		new_Employee.setEmpName("张三");
		new_Employee.setSalary(new BigDecimal(90000));
		new_Employee.setGender("男");
		new_Employee.setJobTitle("法官");
		m_Empolyeemapper.insert(new_Employee);
	}
	@Test
    void test03() {
        System.out.println(m_Empolyeemapper.selectById(1));
		List<employee> list = m_Empolyeemapper.selectList(null);
		for(employee e:list){
            System.out.println(e);
        }
    }
	@Test
    void test04() {
		m_Empolyeemapper.deleteById(2);
		
	}
}
