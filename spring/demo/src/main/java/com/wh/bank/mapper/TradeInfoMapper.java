package com.wh.bank.mapper;

import com.wh.bank.entity.TradeInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 。。。
 * @description 针对表【trade_info】的数据库操作Mapper
 * @createDate 2024-07-04 09:21:41
 * @Entity com.wh.bank.entity.TradeInfo
 */
public interface TradeInfoMapper extends BaseMapper<TradeInfo> {

    @Select("SELECT to_account FROM (SELECT to_account, trade_time,ROW_NUMBER() OVER (PARTITION BY to_account ORDER BY trade_time DESC) AS rn FROM trade_info WHERE from_account = #{accountId}) AS subquery WHERE rn = 1 ORDER BY trade_time DESC LIMIT 5;")
    public List<String> TradetoAccountId(@Param("accountId") String accountId);

    @Select("SELECT from_account FROM (SELECT from_account, trade_time,ROW_NUMBER() OVER (PARTITION BY from_account ORDER BY trade_time DESC) AS rn FROM trade_info WHERE to_account = #{accountId}) AS subquery WHERE rn = 1 ORDER BY trade_time DESC LIMIT 5;")
    public List<String> TradefromAccountId(@Param("accountId") String accountId);
}
