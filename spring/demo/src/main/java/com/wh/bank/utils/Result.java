package com.wh.bank.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private int code;//0:正确的信息
    private String msg;//正确的或者错误的异常的信息
    private T data;//任意类型
    public static <T> Result<T> ok(String msg){
        return new Result<T>(0,msg,null);
    }
    public static <T> Result<T> ok(String msg,T data){
        return new Result<T>(0,msg,data);
    }
    public static <T> Result<T> error(String msg){
        return new Result<T>(1,msg,null);
    }
}
