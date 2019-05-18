package ru.myfirstwebsite.controller.messages;

public class ErrorMessage {
    private Long errorCode;

    private String message;

    public ErrorMessage() {

    }

    public ErrorMessage (String message) {
        this.message = message;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
