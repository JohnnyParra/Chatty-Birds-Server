package com.johnnyparra.real_time_chat.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.AttributeConverter;

public abstract class JsonbConverter<T> implements AttributeConverter<T, String> {
  
  private final ObjectMapper objectMapper;
  private final Class<T> clazz;

  protected JsonbConverter(Class<T> clazz) {
    this.clazz = clazz;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Override
  public String convertToDatabaseColumn(T attribute) {
    try {
      if (attribute == null) {
        return null;
      }
      return objectMapper.writeValueAsString(attribute);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error converting " + clazz.getSimpleName() + " to JSON", e);
    }
  }

  @Override
  public T convertToEntityAttribute(String dbData) {
    try {
      if (dbData == null || dbData.isBlank()) {
        return null;
      }
      return objectMapper.readValue(dbData, clazz);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error converting JSON to " + clazz.getSimpleName(), e);
    }
  }

}
