package com.luthfihariz.almasgocore.model.enums;

public enum EngineType {
    SANDBOX("sandbox"), PRODUCTION("prod");

    private String code;

    private EngineType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
