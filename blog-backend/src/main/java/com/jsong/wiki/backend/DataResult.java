package com.jsong.wiki.backend;

import com.jsong.wiki.backend.exception.code.BaseResponseCode;
import lombok.Data;

/**
 * @Author: Jsong
 * @Date: 2020/3/18 15:20
 * @Description: response返回体
 */
@Data
public class DataResult<T> {
    private int code;
    private String msg;
    private T data;

    public DataResult() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(T data) {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public DataResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public static <T> DataResult success() {
        return new <T>DataResult();
    }

    public static <T> DataResult success(T data) {
        return new <T>DataResult(data);
    }

}
