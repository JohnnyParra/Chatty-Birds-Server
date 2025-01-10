package com.johnnyparra.real_time_chat.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationPreferences {
  @JsonProperty("version")
  private String version;
  @JsonProperty("notifications")
  private Notifications notifications;

  public NotificationPreferences() {
    this.version = "1.0";
    this.notifications = new Notifications();
  }

  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Notifications getNotifications() {
    return this.notifications;
  }

  public void setNotifications(Notifications notifications) {
    this.notifications = notifications;
  }

  public static class Notifications {
    @JsonProperty("messages")
    private Messages messages;
    @JsonProperty("chat_rooms")
    private ChatRooms chatRooms;
    @JsonProperty("user_status")
    private UserStatus userStatus;
    @JsonProperty("app_activity")
    private AppActivity appActivity;
    @JsonProperty("friends")
    private Friends friends;
    @JsonProperty("voice_chat")
    private VoiceChat voiceChat;
    @JsonProperty("screen_sharing")
    private ScreenSharing screenSharing;

    public Notifications() {
      this.messages = new Messages();
      this.chatRooms = new ChatRooms();
      this.userStatus = new UserStatus();
      this.appActivity = new AppActivity();
      this.friends = new Friends();
      this.voiceChat = new VoiceChat();
      this.screenSharing = new ScreenSharing();
    }

    public Messages getMessages() {
      return this.messages;
    }

    public void setMessages(Messages messages) {
      this.messages = messages;
    }

    public ChatRooms getChatRooms() {
      return this.chatRooms;
    }

    public void setChatRooms(ChatRooms chatRooms) {
      this.chatRooms = chatRooms;
    }

    public UserStatus getUserStatus() {
      return this.userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
      this.userStatus = userStatus;
    }

    public AppActivity getAppActivity() {
      return this.appActivity;
    }

    public void setAppActivity(AppActivity appActivity) {
      this.appActivity = appActivity;
    }

    public Friends getFriends() {
      return this.friends;
    }

    public void setFriends(Friends friends) {
      this.friends = friends;
    }

    public VoiceChat getVoiceChat() {
      return this.voiceChat;
    }

    public void setVoiceChat(VoiceChat voiceChat) {
      this.voiceChat = voiceChat;
    }

    public ScreenSharing getScreenSharing() {
      return this.screenSharing;
    }

    public void setScreenSharing(ScreenSharing screenSharing) {
      this.screenSharing = screenSharing;
    }
  }

  public static class Messages {
    @JsonProperty("general")
    private Notification general;
    @JsonProperty("mentions")
    private Notification mentions;
    @JsonProperty("unread")
    private Notification unread;
    @JsonProperty("replies")
    private Notification replies;
    @JsonProperty("likes")
    private Notification likes;

    public Messages() {
      this.general = new Notification(true, false);
      this.mentions = new Notification(true, true);
      this.unread = new Notification(false, true);
      this.replies = new Notification(true, false);
      this.likes = new Notification(true, false);
    }

    public Notification getGeneral() {
      return this.general;
    }

    public void setGeneral(Notification general) {
      this.general = general;
    }

    public Notification getMentions() {
      return this.mentions;
    }

    public void setMentions(Notification mentions) {
      this.mentions = mentions;
    }

    public Notification getUnread() {
      return this.unread;
    }

    public void setUnread(Notification unread) {
      this.unread = unread;
    }

    public Notification getReplies() {
      return this.replies;
    }

    public void setReplies(Notification replies) {
      this.replies = replies;
    }

    public Notification getLikes() {
      return this.likes;
    }

    public void setLikes(Notification likes) {
      this.likes = likes;
    }
  }

  public static class ChatRooms {
    @JsonProperty("user_joined")
    private Notification userJoined;
    @JsonProperty("user_left")
    private Notification userLeft;
    @JsonProperty("room_created")
    private Notification roomCreated;

    public ChatRooms() {
      this.userJoined = new Notification(true, false);
      this.userLeft = new Notification(true, false);
      this.roomCreated = new Notification(true, true);
    }

    public Notification getUserJoined() {
      return this.userJoined;
    }

    public void setUserJoined(Notification userJoined) {
      this.userJoined = userJoined;
    }

    public Notification getUserLeft() {
      return this.userLeft;
    }

    public void setUserLeft(Notification userLeft) {
      this.userLeft = userLeft;
    }

    public Notification getRoomCreated() {
      return this.roomCreated;
    }

    public void setRoomCreated(Notification roomCreated) {
      this.roomCreated = roomCreated;
    }
  }

  public static class UserStatus {
    @JsonProperty("online")
    private Notification online;

    public UserStatus() {
      this.online = new Notification(true, false);
    }

    public Notification getOnline() {
      return this.online;
    }

    public void setOnline(Notification online) {
      this.online = online;
    }
  }

  public static class AppActivity {
    @JsonProperty("updates")
    private Notification updates;
    @JsonProperty("system_issues")
    private Notification systemIssues;

    public AppActivity() {
      this.updates = new Notification(false, false);
      this.systemIssues = new Notification(true, false);
    }

    public Notification getUpdates() {
      return this.updates;
    }

    public void setUpdates(Notification updates) {
      this.updates = updates;
    }

    public Notification getSystemIssues() {
      return this.systemIssues;
    }

    public void setSystemIssues(Notification systemIssues) {
      this.systemIssues = systemIssues;
    }
  }

  public static class Friends {
    @JsonProperty("request")
    private Notification request;
    @JsonProperty("responses")
    private Notification responses;
    @JsonProperty("favorited_online")
    private Notification favoritedOnline;

    public Friends() {
      this.request = new Notification(true, true);
      this.responses = new Notification(true, false);
      this.favoritedOnline = new Notification(true, true);
    }

    public Notification getRequest() {
      return this.request;
    }

    public void setRequest(Notification request) {
      this.request = request;
    }

    public Notification getResponses() {
      return this.responses;
    }

    public void setFriendRequestAccepted(Notification responses) {
      this.responses = responses;
    }

    public Notification getFavoritedOnline() {
      return this.favoritedOnline;
    }

    public void setFavoritedOnline(Notification favoritedOnline) {
      this.favoritedOnline = favoritedOnline;
    }
  }

  public static class VoiceChat {
    @JsonProperty("start")
    private Notification start;
    @JsonProperty("join")
    private Notification join;
    @JsonProperty("leave")
    private Notification leave;
    @JsonProperty("end")
    private Notification end;
    @JsonProperty("mute")
    private Notification mute;

    public VoiceChat() {
      this.start = new Notification(true, true);
      this.join = new Notification(true, true);
      this.leave = new Notification(true, true);
      this.end = new Notification(true, true);
      this.mute = new Notification(true, true);
    }

    public Notification getStart() {
      return this.start;
    }

    public void setStart(Notification start) {
      this.start = start;
    }

    public Notification getJoin() {
      return this.join;
    }

    public void setJoin(Notification join) {
      this.join = join;
    }

    public Notification getLeave() {
      return this.leave;
    }

    public void setLeave(Notification leave) {
      this.leave = leave;
    }

    public Notification getEnd() {
      return this.end;
    }

    public void setEnd(Notification end) {
      this.end = end;
    }

    public Notification getMute() {
      return this.mute;
    }

    public void setMute(Notification mute) {
      this.mute = mute;
    }
  }

  public static class ScreenSharing {
    @JsonProperty("start")
    private Notification start;
    @JsonProperty("join")
    private Notification join;
    @JsonProperty("leave")
    private Notification leave;
    @JsonProperty("end")
    private Notification end;
    @JsonProperty("mute")
    private Notification mute;

    public ScreenSharing() {
      this.start = new Notification(true, true);
      this.join = new Notification(true, true);
      this.leave = new Notification(true, true);
      this.end = new Notification(true, true);
      this.mute = new Notification(true, true);
    }

    public Notification getStart() {
      return this.start;
    }

    public void setStart(Notification start) {
      this.start = start;
    }

    public Notification getJoin() {
      return this.join;
    }

    public void setJoin(Notification join) {
      this.join = join;
    }

    public Notification getLeave() {
      return this.leave;
    }

    public void setLeave(Notification leave) {
      this.leave = leave;
    }

    public Notification getEnd() {
      return this.end;
    }

    public void setEnd(Notification end) {
      this.end = end;
    }

    public Notification getMute() {
      return this.mute;
    }

    public void setMute(Notification mute) {
      this.mute = mute;
    }
  }

  public static class Notification {
    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("sound")
    private boolean sound;

    public Notification() {
      this.enabled = true;
      this.sound = true;
    }

    public Notification(boolean enabled, boolean sound) {
      this.enabled = enabled;
      this.sound = sound;
    }

    public boolean getEnabled() {
      return this.enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public boolean getSound() {
      return this.sound;
    }

    public void setSound(boolean sound) {
      this.sound = sound;
    }
  }
}
