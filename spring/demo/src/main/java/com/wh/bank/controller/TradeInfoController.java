package com.wh.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wh.bank.entity.TradeInfo;
import com.wh.bank.service.TradeInfoService;
import com.wh.bank.utils.Result;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class TradeInfoController {
    
    @Autowired
    private TradeInfoService tradeInfoService;

    @GetMapping("/trades/{page}")
    public Result pageTradeInfo(@PathVariable("page") Integer page){
        IPage<TradeInfo> tradeInfoPage = tradeInfoService.pageTradeInfo(page);
        log.info("查询成功");
        return Result.ok("查询成攻", tradeInfoPage);
    }
    @GetMapping("/@{AccountId}/trades/{page}")
    public Result pageTradeInfo(@PathVariable("AccountId") String AccountId,@PathVariable("page") Integer page){
        IPage<TradeInfo> tradeInfoPage = tradeInfoService.pageTradeInfoAccountId(page,AccountId);
        log.info("查询成功");
        return Result.ok("查询成攻", tradeInfoPage);
    }
}
