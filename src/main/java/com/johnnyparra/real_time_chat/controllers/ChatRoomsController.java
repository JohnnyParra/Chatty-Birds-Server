package com.johnnyparra.real_time_chat.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.johnnyparra.real_time_chat.entities.ChatRooms;
import com.johnnyparra.real_time_chat.repositories.ChatRoomsRepository;

@Controller
public class ChatRoomsController {

  private final ChatRoomsRepository chatRoomsRepository;

  public ChatRoomsController(ChatRoomsRepository chatRoomsRepository) {
    this.chatRoomsRepository = chatRoomsRepository;
  }

  @QueryMapping
  @PreAuthorize("isAuthenticated() and #userId == principal.id")
  public List<ChatRooms> chatRoomsByUserId(Long userId) {
    System.out.println("Fetching chat rooms for user with ID: " + userId);
    return chatRoomsRepository.findByUser_Id(userId);
  }

  @QueryMapping
  @PreAuthorize("isAuthenticated()")
  public ChatRooms chatRoomById(Long id) {
    System.out.println("Fetching chat room with ID: " + id);
    return chatRoomsRepository.findById(id).orElse(null);
  }

  // @MutationMapping
  // public ChatRooms createChatRoom(String roomName, Long userId) {
  //   System.out.println("Creating chat room: " + roomName);
  //   ChatRooms chatRoom = new ChatRooms(roomName);
  //   chatRoom.setUser(userId);
  //   return chatRoomsRepository.save(chatRoom);
  // }

  // @MutationMapping
  // public ChatRooms updateChatRoom(Long id, String roomName, Boolean isPrivate) {
  //   System.out.println("Updating chat room with ID: " + id);
  //   ChatRooms chatRoom = chatRoomsRepository.findById(id).orElse(null);
  //   chatRoom.setRoomName(roomName);
  //   chatRoom.setIsPrivate(isPrivate);
  //   return chatRoomsRepository.save(chatRoom);
  // }

}
