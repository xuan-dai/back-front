package cn.gorillahug.back.front.common;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpResponseHelper {


    public static ResponseEntity<?> buildResponse(int httpCode, int code, String content) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", code);
        body.put("content", content);
        return buildResponse(httpCode, body);
    }

    public static ResponseEntity<?> buildResponse(int httpCode, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        log.info("Response: httpCode:{}, body:{}", httpCode, JSON.toJSONString(body));
        return new ResponseEntity<>(body, headers, HttpStatus.valueOf(httpCode));
    }

    public static ResponseEntity<?> buildResponse(RespCode respCode,Object body){
        Map<String,Object> respBody = new HashMap<>();
        respBody.put("code",respCode.getCode());
        respBody.put("content",body);
        return buildResponse(respCode.getHttpCode(),respBody);
    }

    public static ResponseEntity<?> buildResponse(int httpCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        log.info("Response: httpCode:{}", httpCode);
        return new ResponseEntity<>(headers, HttpStatus.valueOf(httpCode));
    }

    public static ResponseEntity<?> buildResponse(RespCode errorCode) {
        return buildResponse(errorCode.getHttpCode(), errorCode.getCode(), errorCode.getMessage());
    }

    public static void response(HttpServletResponse response, RespCode errorCode) throws IOException {
        response.setContentType("application/json");
        response.setStatus(errorCode.getHttpCode());
        response.getWriter().print(errorCode.toJson());
    }


}
