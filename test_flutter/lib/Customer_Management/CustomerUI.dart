import 'package:flutter/material.dart';
import 'Customer.dart';
import 'CustomerDetailDialog.dart';
import 'CustomerService.dart';

class CustomerUI extends StatefulWidget {
  const CustomerUI({super.key});

  @override
  _CustomerUI createState() => _CustomerUI();
}

class _CustomerUI extends State<CustomerUI> {
  final CustomerService _customerService = CustomerService();
  List<Customer> _customers = [];
  bool _isSearching = false;
  final TextEditingController _searchController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _loadCustomers();
  }

  Future<void> _loadCustomers() async {
    try {
      final customers = await _customerService.getAllCustomers();
      setState(() {
        _customers = customers;
      });
    } catch (e) {
      print('Error loading customers: $e');
    }
  }

  void _filterCustomers(String query) {
    final filteredCustomers = _customers.where((customer) {
      final nameLower = customer.name.toLowerCase();
      final queryLower = query.toLowerCase();
      return nameLower.contains(queryLower);
    }).toList();

    setState(() {
      _filteredCustomers = filteredCustomers;
    });
  }

  List<Customer> _filteredCustomers = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Customer List'),
        actions: [
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              setState(() {
                _isSearching = !_isSearching;
                if (!_isSearching) {
                  _searchController.clear();
                  _filteredCustomers = _customers;
                }
              });
            },
          ),
        ],
      ),
      body: _buildCustomerList(),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _showCreateCustomerDialog(context);
        },
        child: Icon(Icons.add),
      ),
    );
  }

  Widget _buildCustomerList() {
    if (_isSearching) {
      return Column(
        children: [
          Container(
            padding: EdgeInsets.symmetric(horizontal: 16),
            child: TextField(
              controller: _searchController,
              onChanged: (value) {
                _filterCustomers(value);
              },
              decoration: InputDecoration(
                hintText: 'Search...',
                border: InputBorder.none,
              ),
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: _filteredCustomers.length,
              itemBuilder: (context, index) {
                final customer = _filteredCustomers[index];
                return ListTile(
                  leading: Icon(Icons.person),
                  title: Text(customer.name),
                  subtitle: Text(customer.phone),
                  onTap: () {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return CustomerDetailDialog(customer: customer);
                      },
                    );
                  },
                );
              },
            ),
          ),
        ],
      );
    } else {
      return ListView.builder(
        itemCount: _customers.length,
        itemBuilder: (context, index) {
          final customer = _customers[index];
          return ListTile(
            leading: Icon(Icons.person),
            title: Text(customer.name),
            subtitle: Text(customer.phone),
            onTap: () {
              showDialog(
                context: context,
                builder: (BuildContext context) {
                  return CustomerDetailDialog(customer: customer);
                },
              );
            },
            enabled: !customer.disable,
            tileColor: customer.disable ? Colors.grey : null,
          );
        },
      );
    }
  }

  void _showCreateCustomerDialog(BuildContext context) {
    String name = '';
    String email = '';
    String phone = '';
    String address = '';
    String fax = '';

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Create New Customer'),
          content: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextField(
                  decoration: InputDecoration(labelText: 'Name'),
                  onChanged: (value) {
                    setState(() {
                      name = value;
                    });
                  },
                ),
                TextField(
                  decoration: InputDecoration(labelText: 'Email'),
                  onChanged: (value) {
                    setState(() {
                      email = value;
                    });
                  },
                ),
                TextField(
                  decoration: InputDecoration(labelText: 'Phone'),
                  onChanged: (value) {
                    setState(() {
                      phone = value;
                    });
                  },
                ),
                TextField(
                  decoration: InputDecoration(labelText: 'Address'),
                  onChanged: (value) {
                    setState(() {
                      address = value;
                    });
                  },
                ),
                TextField(
                  decoration: InputDecoration(labelText: 'Tax'),
                  onChanged: (value) {
                    setState(() {
                      fax = value;
                    });
                  },
                ),
              ],
            ),
          ),
          actions: [
            ElevatedButton(
              onPressed: () async {
                try {
                  final newCustomer = Customer(
                    name: name,
                    email: email,
                    phone: phone,
                    address: address,
                    fax: fax,
                    disable: false,
                  );

                  final addedCustomer = await _customerService.addCustomer(newCustomer);

                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(
                      content: Text('Customer added successfully: ${addedCustomer.name}'),
                    ),
                  );

                  _loadCustomers();

                  Navigator.of(context).pop();
                } catch (e) {
                  debugPrint('Error adding customer: $e');
                }
              },
              child: Text('Save'),
            ),

            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Cancel'),
            ),
          ],
        );
      },
    );
  }
}
