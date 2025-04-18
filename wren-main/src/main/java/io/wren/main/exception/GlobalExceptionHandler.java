package io.wren.main.exception;


import io.wren.main.enums.ResponseCodeEnum;
import io.wren.main.vo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ApiResult ExceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return ApiResult.fail(ex.getApiResult());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            ApiResult apiResult = ApiResult.fail(ResponseCodeEnum.BIND_ERROR);
            apiResult.setMessage("参数校验异常"+ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return apiResult;
        }
        log.error(e.toString());
        return ApiResult.fail(ApiResult.fail(ResponseCodeEnum.ERROR));
    }
}
