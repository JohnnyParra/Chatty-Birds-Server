package com.johnnyparra.real_time_chat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnnyparra.real_time_chat.entities.ChatRooms;

@Repository
public interface ChatRoomsRepository extends JpaRepository<ChatRooms, Long> {
  List<ChatRooms> findByUser_Id(Long userId);
}
