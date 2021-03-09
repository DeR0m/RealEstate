package com.example.RealEstate.repo;

import com.example.RealEstate.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
