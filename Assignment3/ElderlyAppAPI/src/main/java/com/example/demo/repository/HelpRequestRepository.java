package com.example.demo.repository;

import com.example.demo.model.HelpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {
}