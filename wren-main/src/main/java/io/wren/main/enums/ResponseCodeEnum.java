package com.lsy.seckill.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ResponseCodeEnum{

    //通用
    SUCCESS(200,"请求成功"),
    ERROR(500,"服务器异常"),

    //登录模块

    LOGIN_ERROR(500510,"用户名或密码错误"),
    MOBILE_ERROR(500511,"手机号格式不正确"),
    BIND_ERROR(500512,"参数校验异常"),
    NOT_LOGIN(500513,"未登录，请登录"),

    //秒杀
    SECKILL_STOCK_NOT_ENOUGH(501500, "秒杀商品库存不足"),
    REPEAT_SECKILL(501501,"重复秒杀"),
    SECKILL_SUCCESS(501502, "秒杀成功"),
    SECKILL_ERROR(5001503, "秒杀失败"),

    //下载
    DOWNLOAD_ERROR(502500, "下载失败"),
    ;
    private final Integer CODE;
    private final String MESSAGE;

}
