package com.johnnyparra.real_time_chat.converters;

import com.johnnyparra.real_time_chat.models.AvatarMetadata;

import jakarta.persistence.Converter;

@Converter
public class AvatarMetadataConverter extends JsonbConverter<AvatarMetadata> {

  public AvatarMetadataConverter() {
    super(AvatarMetadata.class);
  }
}
