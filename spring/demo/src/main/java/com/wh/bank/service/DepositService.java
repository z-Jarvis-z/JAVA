package com.wh.bank.service;

import java.util.Date;

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
import com.wh.bank.vo.TradeVo;

@Service
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

        depositMapper.subDepositMoney(tradeVo.getFromAccountId(), tradeVo.getToAccountId(),tradeVo.getMoney());
        depositMapper.addDepositMoney(tradeVo.getFromAccountId(), tradeVo.getToAccountId(),tradeVo.getMoney());

        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setTradeType("同行转账");
        tradeInfo.setFromAccount(tradeVo.getFromAccountId());
        tradeInfo.setToAccount(tradeVo.getToAccountId());
        tradeInfo.setTradeMoney(tradeVo.getMoney());
        tradeInfo.setTradeTime(new Date());
        tradeInfo.setSummary("你向" + toAccount.getIdentityName() + "转账" + tradeVo.getMoney() + "元 备注是"+tradeVo.getRemark());
        tradeInfoMapper.insert(tradeInfo);
    }

}
