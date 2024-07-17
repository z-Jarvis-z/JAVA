package com.wh.bank.mapper;

import com.wh.bank.entity.Deposit;

import java.util.List;

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

    @Select("select * from deposit where account_id = #{accountId} and deposit_type = '死期' AND del_flag = 0 ORDER BY deposit_time DESC ")
    public List<Deposit> getDeposit_deadByAccountId(String accountId);

    @Select("select sum(remaining) from deposit where account_id = #{accountId} and deposit_type = '死期' AND del_flag = 0")
    public Integer getdeadPropertyByAccountId(String accountId);

    @Update("update deposit set remaining = remaining - #{Money} where account_id = #{fromAccountId} and deposit_type = '活期'")
    public void subDepositMoney(@Param("fromAccountId") String fromAccountId, @Param("Money") Integer Money);

    @Update("update deposit set remaining = remaining + #{Money} where account_id = #{toAccountId} and deposit_type = '活期'")
    public void addDepositMoney( @Param("toAccountId") String toAccountId, @Param("Money") Integer Money);

    @Update("update deposit set del_flag = 1 where deposit_id = #{depositId}")
    public void delDeposit_deadByDepoitId(@Param("depositId")Integer depositId);
}




