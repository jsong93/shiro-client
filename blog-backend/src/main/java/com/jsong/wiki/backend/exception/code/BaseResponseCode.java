package com.jsong.wiki.backend.exception.code;

/**
 *
 * @Author: Jsong
 * @Date: 2020/3/18 21:00
 * @Description: 返回response格式
 */
public enum BaseResponseCode implements ResponseCodeInterface{
    SUCCESS(0, "操作成功"),
    NOT_ACCOUNT(401004, "该用户不存在,请先注册"),
    PASSWORD_ERROR(401006, "用户名或密码错误"),
    SYSTEM_BUSY(500001, "系统繁忙，请稍候再试"),
    OPERATION_ERRO(500002, "操作失败");

    private final int code;
    private final String msg;

    BaseResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
