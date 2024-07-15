package com.wh.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.bank.service.DepositService;
import com.wh.bank.utils.Result;
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
}
