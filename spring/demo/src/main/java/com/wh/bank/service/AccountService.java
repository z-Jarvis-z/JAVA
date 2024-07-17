package com.wh.bank.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.bank.entity.Account;
import com.wh.bank.entity.Deposit;
import com.wh.bank.exception.APIexception;
import com.wh.bank.mapper.AccountMapper;
import com.wh.bank.mapper.DepositMapper;
import com.wh.bank.utils.DateTools;
import com.wh.bank.vo.AccountVo;

@Service
public class AccountService {
    @Autowired 
    private AccountMapper accountMapper;

    @Autowired
    private DepositMapper depositMapper;

    public Account loginaccount(String accountId, String password)
    {
        Account account = accountMapper.selectById(accountId);
        if(account == null)
        {
            throw new APIexception("账号不存在");
        }
        if(account.getPassword().equals(password))
        {
            return account;
        }
        throw new APIexception("账号不存在或密码错误");

    }

    @Transactional
    public void registAccount(AccountVo accountVo)
    {
        if(accountMapper.selectById(accountVo.getAccountId())!= null)
        {
            throw new APIexception("账号已存在");
        }
        Account account = new Account();
        account.setAccountId(accountVo.getAccountId());
        account.setPassword(accountVo.getPassword());
        account.setIdentityName(accountVo.getIdentityName());
        account.setMobile(accountVo.getMobile());
        account.setCreateTime(new Date());

        accountMapper.insert(account);

        Deposit deposit = new Deposit();
        deposit.setAccountId(accountVo.getAccountId());
        deposit.setDepositType("活期");;
        deposit.setRemaining(0);
        deposit.setDepositTime(new Date());
        deposit.setExpireTime(DateTools.getYearTime(99));
        deposit.setInterestRate(new BigDecimal(0.002));
        deposit.setRemark(accountVo.getIdentityName()+"新建的银行账户");
        
        depositMapper.insert(deposit);
    }
}
