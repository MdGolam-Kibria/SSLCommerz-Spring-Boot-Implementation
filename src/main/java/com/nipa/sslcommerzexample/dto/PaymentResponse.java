package com.nipa.sslcommerzexample.dto;

import java.util.Map;

public class PaymentResponse {
    private boolean success;
    private String message;
    private Map<String, Object> data;

    // ✅ No-args constructor (needed for Jackson)
    public PaymentResponse() {}

    // ✅ All-args constructor
    public PaymentResponse(boolean success, String message, Map<String, Object> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // ✅ Getters
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public Map<String, Object> getData() {
        return data;
    }

    // ✅ Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
