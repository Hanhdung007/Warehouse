import 'package:flutter/material.dart';
import 'package:test_flutter/screnn/CustomerUI.dart';
import '../model/Customer.dart';
import '../service/CustomerService.dart';

class CreateCustomerPage extends StatefulWidget {
  final String initialName;
  final String initialEmail;
  final String initialPhone;
  final String initialAddress;
  final String initialFax;
  final List<Customer> customers;

  const CreateCustomerPage({
    Key? key,
    required this.customers,
    this.initialName = '',
    this.initialEmail = '',
    this.initialPhone = '',
    this.initialAddress = '',
    this.initialFax = '',
  }) : super(key: key);

  @override
  _CreateCustomerPageState createState() => _CreateCustomerPageState();
}

class _CreateCustomerPageState extends State<CreateCustomerPage> {
  final CustomerService _customerService = CustomerService();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  TextEditingController _nameController = TextEditingController();
  TextEditingController _emailController = TextEditingController();
  TextEditingController _phoneController = TextEditingController();
  TextEditingController _addressController = TextEditingController();
  TextEditingController _faxController = TextEditingController();
  bool _isDisabled = false;

  bool isValidEmail(String email) {
    final emailPattern =
        RegExp(r'^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');
    return emailPattern.hasMatch(email);
  }

  bool isValidPhoneNumber(String phone) {
    final phonePattern = RegExp(r'^\d{10,12}$');
    return phonePattern.hasMatch(phone);
  }

  bool isValidFax(String fax) {
    final faxPattern = RegExp(r'^\d{13}$');
    return faxPattern.hasMatch(fax);
  }

  @override
  void initState() {
    super.initState();
    _nameController.text = widget.initialName;
    _emailController.text = widget.initialEmail;
    _phoneController.text = widget.initialPhone;
    _addressController.text = widget.initialAddress;
    _faxController.text = widget.initialFax;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Create Customer'),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              TextFormField(
                controller: _nameController,
                decoration: InputDecoration(labelText: 'Name'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a customer name.';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _emailController,
                decoration: InputDecoration(labelText: 'Email'),
                validator: (value) {
                  if (value != null &&
                      value.isNotEmpty &&
                      !isValidEmail(value)) {
                    return 'Invalid email format. Please enter a valid email address.';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _phoneController,
                decoration: InputDecoration(labelText: 'Phone'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a phone number.';
                  } else if (!isValidPhoneNumber(value)) {
                    return 'Invalid phone number format. The phone number must have 10 to 12 digits.';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _addressController,
                decoration: InputDecoration(labelText: 'Address'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter an address.';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _faxController,
                decoration: InputDecoration(labelText: 'Tax'),
                validator: (value) {
                  if (value != null && value.isNotEmpty && !isValidFax(value)) {
                    return 'Invalid tax code format. Tax code must have exactly 13 digits.';
                  }
                  return null;
                },
              ),
              SizedBox(height: 16.0),
              Row(
                children: [
                  Text('Disable'),
                  Checkbox(
                    value: _isDisabled,
                    onChanged: (value) {
                      setState(() {
                        _isDisabled = value!;
                      });
                    },
                  ),
                ],
              ),
              ElevatedButton(
                onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    final name = _nameController.text;
                    final email = _emailController.text;
                    final phone = _phoneController.text;
                    final address = _addressController.text;
                    final fax = _faxController.text;

                    final newCustomer = Customer(
                      name: name,
                      email: email,
                      phone: phone,
                      address: address,
                      fax: fax,
                      disable: _isDisabled,
                    );

                    final isCustomerExist = widget.customers.any((customer) =>
                        customer.name == name &&
                        customer.email == email &&
                        customer.phone == phone &&
                        customer.fax == fax);

                    if (isCustomerExist) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(
                          content: Text('Customer already exists.'),
                        ),
                      );
                    } else {
                      try {
                        final addedCustomer =
                            await _customerService.addCustomer(newCustomer);

                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content: Text(
                                'Customer added successfully: ${addedCustomer.name}'),
                          ),
                        );

                        Navigator.pushAndRemoveUntil(
                          context,
                          MaterialPageRoute(builder: (context) => CustomerUI()),
                          (Route<dynamic> route) => false,
                        );
                      } catch (e) {
                        debugPrint('Error adding customer: $e');
                      }
                    }
                  }
                },
                child: Text('Save'),
              ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  void dispose() {
    _nameController.dispose();
    _emailController.dispose();
    _phoneController.dispose();
    _addressController.dispose();
    _faxController.dispose();
    super.dispose();
  }
}
