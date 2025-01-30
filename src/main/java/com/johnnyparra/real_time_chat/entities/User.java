package com.johnnyparra.real_time_chat.entities;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.johnnyparra.real_time_chat.converters.AvatarMetadataConverter;
import com.johnnyparra.real_time_chat.models.AvatarMetadata;

@Entity
@Table(name = "users")
@DynamicUpdate
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name", length = 255, nullable = true)
  private String firstName = null;

  @Column(name = "last_name", length = 255, nullable = true)
  private String lastName = null;

  @Column(name = "username", length = 255, nullable = false, unique = true)
  private String username;

  @Column(name = "email", length = 255, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 255, nullable = false)
  private String password;

  @Column(name = "bio", length = 255, nullable = true)
  private String bio = null;

  @Column(name = "phone_number", length = 15, nullable = true)
  private String phoneNumber = null;

  @Column(name = "avatar_metadata", columnDefinition = "jsonb", nullable = true)
  @Convert(converter = AvatarMetadataConverter.class)
  @ColumnTransformer(write = "?::jsonb")
  private AvatarMetadata avatarMetadata;

  @Column(name = "avatar_metadata", insertable = false, updatable = false)
  private String rawAvatarMetadata;

  @Column(name = "last_active_at")
  private ZonedDateTime lastActiveAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at", nullable = true)
  private ZonedDateTime updatedAt = null;

  @Column(name = "deleted_at", nullable = true)
  private ZonedDateTime deletedAt = null;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserPreferences> userPreferences = new ArrayList<>();

  protected User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = ZonedDateTime.now();
    this.avatarMetadata = new AvatarMetadata();
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public AvatarMetadata getAvatarMetadata() {
    return avatarMetadata;
  }

  public void setAvatarMetadata(AvatarMetadata avatarMetadata) {
    this.avatarMetadata = avatarMetadata;
  }

  public String getRawAvatarMetadata() {
    return rawAvatarMetadata;
  }

  public ZonedDateTime getLastActiveAt() {
    return lastActiveAt;
  }

  public void setLastActiveAt(ZonedDateTime lastActiveAt) {
    this.lastActiveAt = lastActiveAt;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public ZonedDateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(ZonedDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  public List<UserPreferences> getUserPreferences() {
    return userPreferences;
  }

  public void setUserPreferences(List<UserPreferences> userPreferences) {
    this.userPreferences = userPreferences;
  }
}
