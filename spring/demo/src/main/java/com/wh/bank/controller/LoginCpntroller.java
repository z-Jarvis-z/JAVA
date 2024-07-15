package com.wh.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.bank.entity.Account;
import com.wh.bank.service.AccountService;
import com.wh.bank.utils.Result;
import com.wh.bank.vo.AccountVo;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class LoginCpntroller {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Result loginAccount(@RequestParam("accountId") String accountId, @RequestParam("password") String password) {
        log.info("login account,accountId:{},password:{}", accountId, password);
        Account account = accountService.loginaccount(accountId, password);
        
        return Result.ok("登陆成功",account);

        }
    
        @PostMapping("/account/register")
        public Result registeraccount(@Valid AccountVo accountVo,BindingResult bindResult) {
            log.info("register account,accountVo:{}", accountVo);
            if(bindResult.getErrorCount()>0)
            {
                StringBuilder sb = new StringBuilder("校验出错");
                for(ObjectError error:bindResult.getAllErrors())
                {
                    sb.append(error.getDefaultMessage()).append(";");
                }
                return Result.error(sb.toString());
            }
            accountService.registAccount(accountVo);
            return Result.ok("注册成功");
        }
        

}
