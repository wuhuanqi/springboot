package com.wuhuanqi.girl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * 全局异常处理器
 * 用于处理网络连接错误和数据库连接错误
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理数据库连接异常
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException ex) {
        String message = "Connection failed. If the problem persists, please check your internet connection or VPN";
        String details = "Network disconnected [Database Connection Error]";
        
        ErrorResponse errorResponse = new ErrorResponse(message, details);
        
        System.err.println("Database connection error: " + ex.getMessage());
        System.err.println("Request ID: " + errorResponse.getRequestId());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 处理连接异常
     */
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorResponse> handleConnectException(ConnectException ex) {
        String message = "Connection failed. If the problem persists, please check your internet connection or VPN";
        String details = "Network disconnected [unknown]";
        
        ErrorResponse errorResponse = new ErrorResponse(message, details);
        
        System.err.println("Connection error: " + ex.getMessage());
        System.err.println("Request ID: " + errorResponse.getRequestId());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 处理Socket超时异常
     */
    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleSocketTimeoutException(SocketTimeoutException ex) {
        String message = "Connection failed. If the problem persists, please check your internet connection or VPN";
        String details = "Network disconnected [Timeout]";
        
        ErrorResponse errorResponse = new ErrorResponse(message, details);
        
        System.err.println("Socket timeout error: " + ex.getMessage());
        System.err.println("Request ID: " + errorResponse.getRequestId());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
    }

    /**
     * 处理未知主机异常
     */
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ErrorResponse> handleUnknownHostException(UnknownHostException ex) {
        String message = "Connection failed. If the problem persists, please check your internet connection or VPN";
        String details = "Network disconnected [Unknown Host]";
        
        ErrorResponse errorResponse = new ErrorResponse(message, details);
        
        System.err.println("Unknown host error: " + ex.getMessage());
        System.err.println("Request ID: " + errorResponse.getRequestId());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 处理资源访问异常（RestTemplate等）
     */
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException ex) {
        String message = "Connection failed. If the problem persists, please check your internet connection or VPN";
        String details = "Network disconnected [Resource Access Error]";
        
        ErrorResponse errorResponse = new ErrorResponse(message, details);
        
        System.err.println("Resource access error: " + ex.getMessage());
        System.err.println("Request ID: " + errorResponse.getRequestId());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 处理通用异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        // 检查是否是网络相关的异常
        if (isNetworkRelated(ex)) {
            String message = "Connection failed. If the problem persists, please check your internet connection or VPN";
            String details = "Network disconnected [unknown]";
            
            ErrorResponse errorResponse = new ErrorResponse(message, details);
            
            System.err.println("Network-related error: " + ex.getMessage());
            System.err.println("Request ID: " + errorResponse.getRequestId());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        // 其他异常
        ErrorResponse errorResponse = new ErrorResponse(
            "An unexpected error occurred", 
            ex.getMessage()
        );
        
        System.err.println("General error: " + ex.getMessage());
        System.err.println("Request ID: " + errorResponse.getRequestId());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 检查异常是否与网络相关
     */
    private boolean isNetworkRelated(Exception ex) {
        String message = ex.getMessage();
        if (message == null) {
            return false;
        }
        
        return message.toLowerCase().contains("connection") ||
               message.toLowerCase().contains("network") ||
               message.toLowerCase().contains("timeout") ||
               message.toLowerCase().contains("host") ||
               ex.getCause() instanceof ConnectException ||
               ex.getCause() instanceof SocketTimeoutException ||
               ex.getCause() instanceof UnknownHostException;
    }
}