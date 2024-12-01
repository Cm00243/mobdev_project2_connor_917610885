import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'order_page.dart';  // Import the new OrderPage

class BreakfastPage extends StatefulWidget {
  @override
  _BreakfastPageState createState() => _BreakfastPageState();
}

class _BreakfastPageState extends State<BreakfastPage> {
  List<dynamic> breakfastOptions = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchBreakfastOptions();
  }

  Future<void> fetchBreakfastOptions() async {
    try {
      final response = await http.get(Uri.parse('http://10.0.2.2:8080/api/v1/meal/BREAKFAST'));
      if (response.statusCode == 200) {
        setState(() {
          breakfastOptions = json.decode(response.body);
          isLoading = false;
        });
      } else {
        throw Exception('Failed to load breakfast options');
      }
    } catch (e) {
      setState(() {
        isLoading = false;
      });
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Error: ${e.toString()}')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Breakfast Options')),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: breakfastOptions.length,
              itemBuilder: (context, index) {
                final combo = breakfastOptions[index];
                return ListTile(
                  title: Text(combo['items']), // Combo items like "sausage, grit, tea, toast"
                  subtitle: Text('Combo ${combo['id']}'),
                  onTap: () {
                    // Navigate to the order page with the selected meal
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => OrderPage(meal: combo),
                      ),
                    );
                  },
                );
              },
            ),
    );
  }
}