package com.wh.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.TradeInfo;
import com.wh.bank.mapper.TradeInfoMapper;


@Service
public class TradeInfoService {

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    public IPage<TradeInfo> pageTradeInfo(int page) {
        QueryWrapper<TradeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("trade_time");
        Page<TradeInfo> p = new Page<>(page, 5);
        IPage<TradeInfo> pe = tradeInfoMapper.selectPage(p, queryWrapper);
        return pe;
    }

    public IPage<TradeInfo> pageTradeInfoAccountId(int page, String accountId) {
        QueryWrapper<TradeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("trade_time");
        queryWrapper.eq("from_account", accountId).or().eq("to_account", accountId);
        Page<TradeInfo> p = new Page<>(page, 5);
        IPage<TradeInfo> pe = tradeInfoMapper.selectPage(p, queryWrapper);
        return pe;
    }

    public List<String> TradetoContact(String accountId) {

        return tradeInfoMapper.TradetoAccountId(accountId);
    }

    public List<String> TradefromContact(String accountId) {

        return tradeInfoMapper.TradefromAccountId(accountId);
    }
}