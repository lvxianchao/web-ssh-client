package com.lvxianchao.api.exception;

import com.lvxianchao.api.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public final class ServiceException extends RuntimeException {

    /**
     * 异常状态码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private String message;

    public ServiceException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ServiceException(String message) {
        this.code = ResponseCode.ERROR.getCode();
        this.message = message;
    }
}
