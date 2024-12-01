// lib/screens/request_meal_page.dart
import 'package:flutter/material.dart';
import 'breakfast_page.dart';
import '../app_drawer.dart';

class RequestMealPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Request Meal")),
      drawer: const AppDrawer(),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Select a Meal Time:',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    // Navigate to Breakfast Page
                    Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => BreakfastPage()),
                    );
                  },
                  child: const Text("BREAKFAST"),
                ),
                const SizedBox(width: 20),
                ElevatedButton(
                  onPressed: () {
                    // Placeholder for Lunch
                  },
                  child: const Text("LUNCH"),
                ),
                const SizedBox(width: 20),
                ElevatedButton(
                  onPressed: () {
                    // Placeholder for Dinner
                  },
                  child: const Text("DINNER"),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}