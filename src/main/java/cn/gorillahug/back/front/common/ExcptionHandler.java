package cn.gorillahug.back.front.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExcptionHandler {
    protected Logger Log = LoggerFactory.getLogger(getClass().getName());

    @ExceptionHandler({ Exception.class, RuntimeException.class })
    public ResponseEntity<?> unknownErrorHandler(Exception e) {
        Log.error("捕获到未知异常（500），请检查：" + e.getMessage(), e);
        return HttpResponseHelper.buildResponse(RespCode.SERVER_ERROR);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<?> serviceErrorHandler(ServiceException e) {
        return HttpResponseHelper.buildResponse(e.getErrorCode());
    }

    //校验没通过,会抛出这个异常
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> validationErrorHandler(ConstraintViolationException e) {
        return HttpResponseHelper.buildResponse(RespCode.PARAMETER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> validationErrorHandler(MethodArgumentNotValidException e) {
        return HttpResponseHelper.buildResponse(RespCode.PARAMETER_ERROR);
    }

    /*TypeMismatchException.class Path params format失败异常*/
    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<?> typeMissmatchErrorHandler(TypeMismatchException e) {
        return HttpResponseHelper.buildResponse(RespCode.PARAMETER_ERROR);
    }

    /**
     * HttpMessageNotReadableException.class Body无法编译异常
     * MissingServletRequestParameterException.class 缺少RequestParams异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<?> parameterErrorHandler(Exception e) {
        Log.debug("catch parameter exception", e);
        return HttpResponseHelper.buildResponse(RespCode.PARAMETER_ERROR);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> methodNotSupportErrorHandler(Exception e) {
        Log.debug("catch method not support exception", e);
        return HttpResponseHelper.buildResponse(RespCode.REQUEST_METHOD_NOT_SUPPORT);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<?> httpMediaTypeErrorHandler() {
        return HttpResponseHelper.buildResponse(RespCode.HTTP_MEDIA_TYPE_NOT_SUPPORT);
    }

}
