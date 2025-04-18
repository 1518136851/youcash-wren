package io.wren.main.vo;

import io.wren.main.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class ApiResult {

    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    public ApiResult() {}

    public ApiResult(Boolean success, ResponseCodeEnum responseCodeEnum) {
        this.success = success;
        this.code = responseCodeEnum.getCODE();
        this.message = responseCodeEnum.getMESSAGE();
    }

    public ApiResult(Boolean success, ResponseCodeEnum responseCodeEnum, Object data) {
        this(success, responseCodeEnum);
        this.data = data;
    }

    public static ApiResult fail(ResponseCodeEnum responseCodeEnum) {
        return new ApiResult(false, responseCodeEnum);
    }

    public static ApiResult fail(ApiResult apiResult){
        return apiResult;
    }


    public static ApiResult success(ResponseCodeEnum responseCodeEnum,Object data) {
        return new ApiResult(true, responseCodeEnum, data);
    }

    public static ApiResult success(ResponseCodeEnum responseCodeEnum) {
        return new ApiResult(true, responseCodeEnum);
    }
}