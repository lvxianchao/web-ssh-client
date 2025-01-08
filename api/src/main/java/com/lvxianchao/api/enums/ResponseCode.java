package com.lvxianchao.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "操作成功"),
    LOGIN_SUCCESS(200, "登录成功"),

    ERROR(400, "操作失败"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHENTICATED(401, "请先登录"),
    UNAUTHORIZED(403, "无权操作"),
    NOT_FOUND(404, "数据不存在"),

    SERVER_ERROR(500, "服务器异常"),

    // end
    ;

    private final Integer code;

    private final String message;

}
