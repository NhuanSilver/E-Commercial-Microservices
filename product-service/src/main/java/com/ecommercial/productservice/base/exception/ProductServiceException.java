package com.ecommercial.productservice.base.exception;


import lombok.Data;

@Data
public class ProductServiceException extends Exception {
    protected String errorCode;
    protected String errorMessage;
    protected String errorDetail;

    public ProductServiceException(String errorCode, String errorMessage, String errorDetail) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public ProductServiceException(String message, Throwable cause, String errorCode, String errorMessage, String errorDetail) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
