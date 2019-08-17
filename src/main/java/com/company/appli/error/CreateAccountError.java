package com.company.appli.error;

public class CreateAccountError extends Error {
    int errorCode;
    String errorMessage;

    public CreateAccountError(String message) {
        super(message);
    }

    public CreateAccountError(Throwable throwable) {
        super(throwable);
    }

    public CreateAccountError(int erroCode, String errorMessage) {
        this.errorCode = erroCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

