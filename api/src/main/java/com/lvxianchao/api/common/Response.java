package com.lvxianchao.api.common;

import com.lvxianchao.api.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Response<T> success() {
        return new Response<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    public static <T> Response<T> success(ResponseCode responseCode) {
        return new Response<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> Response<T> success(String message) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), message, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Response<T> success(ResponseCode responseCode, T data) {
        return new Response<>(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> Response<T> error() {
        return new Response<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), null);
    }

    public static <T> Response<T> error(ResponseCode responseCode) {
        return new Response<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> Response<T> error(Integer code, String message) {
        return new Response<>(code, message, null);
    }

    public static <T> Response<T> error(HttpStatus status, String message) {
        return new Response<>(status.value(), message, null);
    }

}
