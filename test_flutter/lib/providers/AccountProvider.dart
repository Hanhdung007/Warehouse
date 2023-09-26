import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import '../model/Account.dart';

class AccountProvider extends ChangeNotifier {
  Account? _account;

  Account? get account => _account;


  void setAccount(Account account) {
    _account = account;
    notifyListeners();
  }

  Future<void> logOut(BuildContext context) async {
    notifyListeners();
    Navigator.pushNamedAndRemoveUntil(context, "/login", (route) => false);
  }
}
