package com.lvxianchao.api.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.lvxianchao.api.common.Response;
import com.lvxianchao.api.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionInterceptor {

    /**
     * 全局异常处理
     *
     * @param e Exception
     * @return ResponseCode
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<ResponseCode> handler(Exception e) {
        log.error("【服务器异常】{}", e.getMessage(), e);

        return Response.error(ResponseCode.SERVER_ERROR);
    }

    /**
     * 401 认证失败 NotLoginException
     *
     * @param exception NotLoginException
     * @return Response
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response<ResponseCode> handler(NotLoginException exception) {
        log.warn("认证失败：{}", exception.getMessage());
        return Response.error(ResponseCode.UNAUTHENTICATED);
    }

    /**
     * 业务异常
     *
     * @param exception ServiceException
     * @return Response
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<ResponseCode> handler(ServiceException exception) {
        log.warn("业务异常：{}", exception.getMessage());
        return Response.error(exception.getCode(), exception.getMessage());
    }

    /**
     * 没有传入请求参数，或者参数解析失败
     *
     * @param e HttpMessageNotReadableException
     *
     * @return ResponseCode
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<ResponseCode> handler(HttpMessageNotReadableException e) {
        return Response.error(ResponseCode.PARAM_ERROR);
    }

    /**
     * 404 NoHandlerFoundException
     *
     * @param e NoHandlerFoundException
     *
     * @return Response
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Object> handler(NoHandlerFoundException e) {
        return Response.error(HttpStatus.NOT_FOUND, "接口地址不存在: " + e.getRequestURL());
    }

    /**
     * 请求方式不允许
     *
     * @param exception HttpRequestMethodNotSupportedException
     *
     * @return Response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response<ResponseCode> handler(HttpRequestMethodNotSupportedException exception) {
        log.warn(exception.getMessage());
        return Response.error(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage());
    }

    /**
     * 422 参数校验异常
     *
     * @param e MethodArgumentNotValidException
     *
     * @return Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Response<ResponseCode> handler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        log.warn(message);
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}
