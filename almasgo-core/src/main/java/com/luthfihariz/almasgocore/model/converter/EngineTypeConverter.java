package com.luthfihariz.almasgocore.model.converter;


import com.luthfihariz.almasgocore.model.enums.EngineType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EngineTypeConverter implements AttributeConverter<EngineType, String> {

    @Override
    public String convertToDatabaseColumn(EngineType attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getCode();
    }

    @Override
    public EngineType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(EngineType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
