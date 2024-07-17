package com.wh.bank.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DepositVo {
    private String accountId;

    private String depositType;

    private Integer remaining;
    private Integer years;

    private String remark;
}
