package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.HelpRequest;

@Service
public class HelpRequestService {

    // Method to log a help request
    public void logHelpRequest(HelpRequest helpRequest) {
        // Logic to handle the help request
        // E.g., save to a database, send an email, etc.
        System.out.println("Help request received: " + helpRequest.getRequestDetails());
    }
}