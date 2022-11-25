package io.yapix.base.sdk.yapi.model;

import lombok.Data;

@Data
public class TestResult {

    private Code code;
    private String message;
    private String cookies;

    public enum Code {
        OK,
        AUTH_ERROR,
        NETWORK_ERROR
    }
}
