import 'package:http/http.dart' as http;
import 'dart:convert';

import '../model/Customer.dart';


class CustomerService {
  final String baseUrl = 'http://172.16.1.89:9999/api/customers';

  static final CustomerService _instance = CustomerService._internal();

  factory CustomerService() {
    return _instance;
  }

  CustomerService._internal();

  Future<List<Customer>> getAllCustomers() async {
    final response = await http.get(Uri.parse(baseUrl));

    if (response.statusCode == 200) {
      final List<dynamic> jsonList = json.decode(response.body);
      return jsonList.map((json) => Customer.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load customers');
    }
  }

  Future<Customer> getCustomerByID(int customerID) async {
    final response = await http.get(Uri.parse('$baseUrl/$customerID'));

    if (response.statusCode == 200) {
      return Customer.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load customer');
    }
  }

  Future<Customer> addCustomer(Customer customer) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(customer.toJson()),
    );

    if (response.statusCode == 201) {
      return Customer.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to add customer');
    }
  }

  Future<Customer> updateCustomer(int customerID, Customer customer) async {
    final response = await http.put(
      Uri.parse('$baseUrl/$customerID'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(customer.toJson()),
    );

    if (response.statusCode == 200) {
      return Customer.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to update customer');
    }
  }
}
