import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import '../constaints/urlAPI.dart';
import '../model/User.dart';

class UserProvider with ChangeNotifier {
  late List<User> _users = [];
  late List<User> _filteredUsers = [];

  final Map<String, List<String>> accountCodeMap = {
    'admin': ['Admin'],
    'sale': ['Sale Order'],
    'importer': ['Importer'],
    'whManager': ['Warehouse Manager'],
    'qc': ['Quantity Control'],
  };

  List<User> get users => _filteredUsers.isNotEmpty ? _filteredUsers : _users;

  void searchUsers(String searchTerm) {
    if (searchTerm.isNotEmpty) {
      _filteredUsers = _users.where((user) {
        return user.name!.toLowerCase().contains(searchTerm.toLowerCase()) ||
            user.email!.toLowerCase().contains(searchTerm.toLowerCase()) ||
            user.code!.toLowerCase().contains(searchTerm.toLowerCase()) ||
            user.phone!.toLowerCase().contains(searchTerm.toLowerCase()) ||
            _checkRole(user, searchTerm.toLowerCase());
      }).toList();
    } else {
      _filteredUsers = [];
    }
    notifyListeners();
  }

  bool _checkRole(User user, String searchTerm) {
    for (String code in user.accountCode!.split(' | ')) {
      if (accountCodeMap[code.trim()]!.join(', ').toLowerCase().contains(searchTerm)) {
        return true;
      }
    }
    return false;
  }

  Future<void> getUsers() async {
    final response = await http.get(Uri.parse(userAPI));
    if (response.statusCode == 200) {
      final jsonData = await json.decode(response.body);
      _users = List<User>.from(jsonData.map((data) => User.fromJson(data)));

      _filteredUsers = _users;
      notifyListeners();
    } else {
      throw Exception('Failed To Load Account!');
    }
  }
}