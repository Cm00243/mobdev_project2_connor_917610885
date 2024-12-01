import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class OrderPage extends StatelessWidget {
  final dynamic meal;

  // Constructor to receive the meal details
  OrderPage({required this.meal});

  Future<void> placeOrder(BuildContext context) async {
    try {
      final response = await http.post(
        Uri.parse('http://10.0.2.2:8080/mealorders'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode({
          'mealId': meal['id'],
          'mealType': meal['mealType'],
          'items': meal['items'],
        }),
      );

      if (response.statusCode == 201) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Order placed successfully!')),
        );
      } else {
        throw Exception('Failed to place order');
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Error: ${e.toString()}')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Order Confirmation')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text('Meal: ${meal['items']}', style: TextStyle(fontSize: 20)),
            SizedBox(height: 20),
            Text('Are you sure you want to order this meal?', style: TextStyle(fontSize: 16)),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => placeOrder(context),
              child: Text('Place Order'),
            ),
          ],
        ),
      ),
    );
  }
}