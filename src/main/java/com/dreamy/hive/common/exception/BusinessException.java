package com.dreamy.hive.common.exception;

import com.dreamy.hive.common.api.IErrorCode;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {
    
    private IErrorCode errorCode;
    
    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
