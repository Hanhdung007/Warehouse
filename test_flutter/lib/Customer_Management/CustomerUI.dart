import 'package:flutter/material.dart';
import 'package:test_flutter/main.dart';
import '../model/Customer.dart';
import '../service/CustomerService.dart';
import 'CustomerDetailDialog.dart';
import 'CreateCustomerPage.dart';

class CustomerUI extends StatefulWidget {
  const CustomerUI({Key? key}) : super(key: key);

  @override
  _CustomerUIState createState() => _CustomerUIState();
}

class _CustomerUIState extends State<CustomerUI> {
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
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (context) => const MyApp()));
          },
        ),
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
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => CreateCustomerPage(customers: _customers),
            ),
          );
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
            tileColor: customer.disable ? Colors.grey : null,
          );
        },
      );
    }
  }
}
