package com.wh.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.bank.entity.Deposit;
import com.wh.bank.service.DepositService;
import com.wh.bank.utils.Result;
import com.wh.bank.vo.DepositVo;
import com.wh.bank.vo.TradeVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DepositController {
    
    @Autowired
    private DepositService depositService;

    @PostMapping("/deposit/trade")
    public Result tradeDeposit(TradeVo tradeVo)
    {
        log.info("tradeDeposit: {}", tradeVo);
        depositService.tradeDeposit(tradeVo);
        return Result.ok("success");
    }
    @PostMapping("/property")
    public Result getProperty(@RequestParam("accountId") String accountId)
    {
        Integer property = depositService.getProperty(accountId);
        return Result.ok("查询成攻", property);
    }
    @PostMapping("/deadproperty")
    public Result getDeadProperty(@RequestParam("accountId") String accountId)
    {
        List<Deposit> property = depositService.getDeadProperty(accountId);
        return Result.ok("查询成攻", property);
    }
    @PostMapping("/deadpropertys")
    public Result getDeadPropertys(@RequestParam("accountId") String accountId)
    {
        Integer property = depositService.getDeadSum(accountId);
        return Result.ok("查询成攻", property);
    }
    @PostMapping("/adddeadproperty")
    public Result addDeadProperty(DepositVo depositVo)
    {
        depositService.addDeadProperty(depositVo);
        return Result.ok("success");
    }

    @PostMapping("/resurrect")
    public Result resurrect(@RequestParam("accountId") String accountId,@RequestParam("password") String password,@RequestParam("depositId") Integer depositId)
    {
        depositService.resurrect(accountId,password,depositId);
        return Result.ok("success");
    }
}
