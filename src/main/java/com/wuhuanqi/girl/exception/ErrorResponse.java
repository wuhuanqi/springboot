package com.wuhuanqi.girl.exception;

import java.util.UUID;

/**
 * 错误响应实体类
 */
public class ErrorResponse {
    private String message;
    private String details;
    private String requestId;
    private long timestamp;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
        this.requestId = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}