import 'package:flutter/material.dart';
import '../model/Customer.dart';


class CustomerDetailDialog extends StatelessWidget {
  final Customer customer;
 const CustomerDetailDialog({super.key, required this.customer});


  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(
        'Customer Details',
        style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18),
      ),
      content: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,
          children: [
            ListTile(
              title: Text(
                'Name:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              subtitle: Text(customer.name),
            ),
            ListTile(
              title: Text(
                'Phone:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              subtitle: Text(customer.phone),
            ),
            ListTile(
              title: Text(
                'Email:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              subtitle: Text(customer.email),
            ),
            ListTile(
              title: Text(
                'Address:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              subtitle: Text(customer.address),
            ),
            ListTile(
              title: Text(
                'Tax:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              subtitle: Text(customer.fax),
            ),
            ListTile(
              title: Text(
                'Disabled:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              subtitle: Text(customer.disable ? 'Yes' : 'No'),
            ),
          ],
        ),
      ),
      actions: [
        ElevatedButton(
          onPressed: () {
            Navigator.of(context).pop(); // Đóng dialog khi nhấn nút Close
          },
          child: Text('Close'),
        ),
      ],
    );
  }
}

