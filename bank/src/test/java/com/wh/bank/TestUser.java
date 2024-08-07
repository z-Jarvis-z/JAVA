package com.wh.bank;

import com.wh.bank.entity.User;
import com.wh.bank.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestUser {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        User user=new User();
        user.setLoginName("admin");
        user.setPassword("admin");
        user.setUserName("郑键祥");
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }
    @Test
    public void test02(){
        User user=userMapper.findUserByLoginName("admin");
        System.out.println(user);
    }
    @Test
    public void test03(){
        User user=userMapper.selectByLoginName("admin");
        System.out.println(user);
    }
}
