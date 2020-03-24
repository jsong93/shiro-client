package com.jsong.wiki.backend.exception;

import com.jsong.wiki.backend.exception.code.ResponseCodeInterface;
import lombok.Data;

/**
 * @Author: Jsong
 * @Date: 2020/3/19 21:26
 * @Description: 自定义异常处理
 */

@Data
public class BusinessException extends RuntimeException {
    // 异常码
    private final int messageCode;
    // 异常信息
    private final String message;

    public BusinessException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.message = message;
    }

    public BusinessException(ResponseCodeInterface responseCodeInterface) {
        this(responseCodeInterface.getCode(), responseCodeInterface.getMsg());
    }

}
