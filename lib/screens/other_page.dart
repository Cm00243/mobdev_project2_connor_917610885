import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class OtherPage extends StatefulWidget {
  @override
  _OtherPageState createState() => _OtherPageState();
}

class _OtherPageState extends State<OtherPage> {
  // Form field controllers
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _helpTypeController = TextEditingController();

  // Function to send a help request
  Future<void> _sendHelpRequest() async {
    // Get input data from the user
    String requesterName = _nameController.text;
    String helpType = _helpTypeController.text;

    // Check if both fields are not empty
    if (requesterName.isEmpty || helpType.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Please fill out all fields')),
      );
      return;
    }

    // Prepare the data to send
    Map<String, String> helpRequestData = {
      'requesterName': requesterName,
      'helpType': helpType,
    };

    try {
      // Make the POST request to the Spring Boot API
      final response = await http.post(
        Uri.parse('http://10.0.2.2:8080/helprequests'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode(helpRequestData),
      );

      // Check if the request was successful
      if (response.statusCode == 201) {
        // Successfully created the request
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Help request submitted successfully!')),
        );
        // Optionally, clear the fields after submission
        _nameController.clear();
        _helpTypeController.clear();
      } else {
        // If the server returns an error
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Failed to submit the request')),
        );
      }
    } catch (e) {
      // Handle any error during the request
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Error occurred: $e')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Request Help")),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Text Field for Requester's Name
            TextField(
              controller: _nameController,
              decoration: InputDecoration(
                labelText: 'Your Name',
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 16),

            // Text Field for Help Type
            TextField(
              controller: _helpTypeController,
              decoration: InputDecoration(
                labelText: 'Help Type (e.g. Medical, Feeding, Other)',
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 16),

            // Submit Button
            ElevatedButton(
              onPressed: _sendHelpRequest,
              child: Text('Submit Help Request'),
            ),
          ],
        ),
      ),
    );
  }
}