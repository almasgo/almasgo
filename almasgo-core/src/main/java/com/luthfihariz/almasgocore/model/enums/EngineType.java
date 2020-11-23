package com.luthfihariz.almasgocore.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EngineType {
    SANDBOX("sandbox"), PRODUCTION("prod");

    private String code;

    private EngineType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
