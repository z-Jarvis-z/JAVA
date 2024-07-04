package com.wh.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName deposit
 */
@TableName(value ="deposit")
@Data
public class Deposit implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer depositId;

    private String accountId;

    private String depositType;

    private Integer remaining;

    private Date depositTime;

    private Date expireTime;

    private BigDecimal interestRate;

    private Integer delFlag;

    private String remark;

    private static final long serialVersionUID = 1L;
}