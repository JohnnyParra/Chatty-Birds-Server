package com.johnnyparra.real_time_chat.models;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvatarMetadata {
  @JsonProperty("small")
  private AvatarSize small;

  @JsonProperty("large")
  private AvatarSize large;

  @JsonProperty("original_size")
  private String originalSize;

  @JsonProperty("created_at")
  private ZonedDateTime createdAt;

  public AvatarMetadata() {
    this.small = new AvatarSize("", "0x0", "unknown");
    this.large = new AvatarSize("", "0x0", "unknown");
    this.originalSize = "unknown";
    this.createdAt = ZonedDateTime.now();
  }

  @Override
  public String toString() {
    return "AvatarMetadata{" +
        "small=" + small +
        ", large=" + large +
        ", original_size='" + originalSize + '\'' +
        ", created_at=" + createdAt +
        '}';
  }

  public AvatarSize getSmall() {
    return small;
  }

  public void setSmall(AvatarSize small) {
    this.small = small;
  }

  public AvatarSize getLarge() {
    return large;
  }

  public void setLarge(AvatarSize large) {
    this.large = large;
  }

  public String getOriginalSize() {
    return this.originalSize;
  }

  public void setOriginalSize(String originalSize) {
    this.originalSize = originalSize;
  }

  public ZonedDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt() {
    this.createdAt = ZonedDateTime.now();
  }

  public static class AvatarSize {
    @JsonProperty("url")
    private String url;

    @JsonProperty("size")
    private String size;

    @JsonProperty("image_type")
    private String imageType;

    public AvatarSize() {
    }

    public AvatarSize(String url, String size, String imageType) {
      this.url = url;
      this.size = size;
      this.imageType = imageType;
    }

    @Override
    public String toString() {
      return "AvatarSize{" +
          "url='" + url + '\'' +
          ", size='" + size + '\'' +
          ", image_type='" + imageType + '\'' +
          '}';
    }

    public String getUrl() {
      return this.url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getSize() {
      return this.size;
    }

    public void setSize(String size) {
      this.size = size;
    }

    public String getImageType() {
      return this.imageType;
    }

    public void setImageType(String imageType) {
      this.imageType = imageType;
    }
  }
}
