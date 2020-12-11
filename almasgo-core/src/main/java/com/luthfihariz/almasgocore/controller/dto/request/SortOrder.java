package com.luthfihariz.almasgocore.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortOrder {

    ASCENDING("asc"), DESCENDING("desc");

    private String code;

    private SortOrder(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }


}
