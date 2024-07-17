package com.wh.bank.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.bank.entity.Account;
import com.wh.bank.entity.Deposit;
import com.wh.bank.entity.TradeInfo;
import com.wh.bank.exception.APIexception;
import com.wh.bank.mapper.AccountMapper;
import com.wh.bank.mapper.DepositMapper;
import com.wh.bank.mapper.TradeInfoMapper;
import com.wh.bank.utils.DateTools;
import com.wh.bank.vo.DepositVo;
import com.wh.bank.vo.TradeVo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DepositService {

    @Autowired
    private DepositMapper depositMapper;

    @Autowired
    private TradeInfoMapper tradeInfoMapper; 

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public void tradeDeposit(TradeVo tradeVo) {
        Deposit fromdeposit = depositMapper.getDepositByAccountId(tradeVo.getFromAccountId());

        if (fromdeposit == null) {
            throw new APIexception("错误的请求");
        }
        Deposit todeposit = depositMapper.getDepositByAccountId(tradeVo.getToAccountId());

        if (todeposit == null) {
            throw new APIexception("错误的请求");
        }

        Account toAccount = accountMapper.selectById(tradeVo.getToAccountId());

        if(toAccount == null)
        {
            throw new APIexception("错误的请求");
        }
        if (fromdeposit.getRemaining() < tradeVo.getMoney()) {
            throw new APIexception("余额不足");
        }

        depositMapper.subDepositMoney(tradeVo.getFromAccountId(),tradeVo.getMoney());
        depositMapper.addDepositMoney(tradeVo.getToAccountId(),tradeVo.getMoney());

        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setTradeType("同行转账");
        tradeInfo.setFromAccount(tradeVo.getFromAccountId());
        tradeInfo.setToAccount(tradeVo.getToAccountId());
        tradeInfo.setTradeMoney(tradeVo.getMoney());
        tradeInfo.setTradeTime(new Date());
        tradeInfo.setSummary("你向" + toAccount.getIdentityName() + "转账" + tradeVo.getMoney() + "元 备注是"+tradeVo.getRemark());
        tradeInfoMapper.insert(tradeInfo);
    }

    public Integer getProperty(String accountId) {
        if(depositMapper.getDepositByAccountId(accountId) == null)
        {
            throw new APIexception("找不到id");
        }
        return depositMapper.getDepositByAccountId(accountId).getRemaining();
    }

    @Transactional
    public void addDeadProperty(DepositVo depositVo) {
        if(accountMapper.selectById(depositVo.getAccountId()) == null)
        {
            throw new APIexception("账号不存在");
        }

        if (getProperty(depositVo.getAccountId()) < depositVo.getRemaining()) {
            throw new APIexception("余额不足");
        }

        if (depositVo.getDepositType().equals("活期"))
        {
            throw new APIexception("大哥这个函数是存死期的，你调用错了");
        }

        Deposit deposit = new Deposit();
        deposit.setAccountId(depositVo.getAccountId());

        depositMapper.subDepositMoney(depositVo.getAccountId(), depositVo.getRemaining());
        deposit.setRemaining(depositVo.getRemaining());

        deposit.setDelFlag(0);
        deposit.setDepositType("死期");
        deposit.setInterestRate(new BigDecimal(0.004));
        deposit.setDepositTime( new Date());
        deposit.setExpireTime(DateTools.getYearTime(depositVo.getYears()));
        deposit.setRemark(depositVo.getRemark());
        depositMapper.insert(deposit);

    }
    public List<Deposit> getDeadProperty(String accountId)
    {
        return depositMapper.getDeposit_deadByAccountId(accountId);
    }

    public Integer getDeadSum(String accountId)
    {
        return depositMapper.getdeadPropertyByAccountId(accountId);
    }

    @Transactional
    public void resurrect(String accountId,String password ,Integer depositId)
    {
        Account account = accountMapper.selectById(accountId);
        if(account == null)
        {
            throw new APIexception("账号不存在");
        }
        if(!account.getPassword().equals(password))
        {
            throw new APIexception("密码错误");
        }
        Deposit deposit = depositMapper.selectById(depositId);
        if(deposit == null)
        {
            throw new APIexception("存款不存在");
        }
        if(deposit.getDelFlag() != 0)
        {
            throw new APIexception("存款已删除");
        }
        if(deposit.getDepositType().equals("活期"))
        {
            throw new APIexception("活期存款不能恢复");
        }
        depositMapper.addDepositMoney(accountId,deposit.getRemaining());
        depositMapper.delDeposit_deadByDepoitId(depositId);

    }
}
