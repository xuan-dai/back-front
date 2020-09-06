package cn.gorillahug.back.front.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public enum RespCode {

    // 通用错误
    SERVER_ERROR(500, 5000, "bad server"),
    OK(200, Integer.valueOf(0), "success"),

    OBJECT_NOT_FOUND(400, 4005, " No object find  "),
    REQUEST_METHOD_NOT_SUPPORT(405, 4001, "request method not supported"),
    REQUEST_API_NOT_FOUND(404, 4002, "request api not found"),
    HTTP_MEDIA_TYPE_NOT_SUPPORT(406, 4003, "http media type not supported"),
    PARAMETER_ERROR(400, 4004, "parameter invalid"),
    ;

    private int httpCode;
    private int code;
    private String message;

    RespCode(int httpCode, int code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);
        return JSON.toJSONString(result);
    }

    @Override
    public String toString() {
        return String.format("[httpCode:%s, code:%s, message:%s]", httpCode, code, message);
    }
}