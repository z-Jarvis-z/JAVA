package com.wh.bank.mapper;

import com.wh.bank.entity.Deposit;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 。。。
* @description 针对表【deposit】的数据库操作Mapper
* @createDate 2024-07-04 09:21:41
* @Entity com.wh.bank.entity.Deposit
*/
public interface DepositMapper extends BaseMapper<Deposit> {

    @Select("select * from deposit where account_id = #{accountId} and deposit_type = '活期'")
    public Deposit getDepositByAccountId(String accountId);

    @Update("update deposit set remaining = remaining - #{Money} where account_id = #{fromAccountId} and deposit_type = '活期'")
    public void subDepositMoney(@Param("fromAccountId") String fromAccountId, @Param("toAccountId") String toAccountId, @Param("Money") Integer Money);

    @Update("update deposit set remaining = remaining + #{Money} where account_id = #{toAccountId} and deposit_type = '活期'")
    public void addDepositMoney(@Param("fromAccountId") String fromAccountId, @Param("toAccountId") String toAccountId, @Param("Money") Integer Money);
}




