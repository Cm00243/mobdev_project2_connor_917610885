package com.example.demo.controller;

import com.example.demo.model.HelpRequest;
import com.example.demo.repository.HelpRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/helprequests")
public class HelpRequestController {

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    // Create a new help request
    @PostMapping
    public ResponseEntity<HelpRequest> createHelpRequest(@RequestBody HelpRequest helpRequest) {
        helpRequest.setRequestTime(LocalDateTime.now()); // Set the current time
        HelpRequest savedRequest = helpRequestRepository.save(helpRequest);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    // Get all help requests
    @GetMapping
    public ResponseEntity<List<HelpRequest>> getAllHelpRequests() {
        List<HelpRequest> requests = helpRequestRepository.findAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // Get details of a specific help request by ID
    @GetMapping("/{id}")
    public ResponseEntity<HelpRequest> getRequestDetails(@PathVariable Long id) {
        Optional<HelpRequest> helpRequest = helpRequestRepository.findById(id);
        
        if (helpRequest.isPresent()) {
            return new ResponseEntity<>(helpRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}