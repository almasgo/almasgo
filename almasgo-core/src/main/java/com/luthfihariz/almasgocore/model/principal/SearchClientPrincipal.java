package com.luthfihariz.almasgocore.model.principal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchClientPrincipal {
    private Long userId;
    private Long engineId;
}
