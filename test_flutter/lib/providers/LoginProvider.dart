import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:test_flutter/model/Account.dart';


import '../constaints/urlAPI.dart';
import '../widgets/ExampleSnackbar.dart';
import 'AccountProvider.dart';

class LoginProvider extends ChangeNotifier {
  bool _Loading = false;

  bool get Loading => _Loading;

  Future<void> setLoading(bool value) async {
    _Loading = value;

    notifyListeners();
  }

  Future<void> dangnhap(String mail, String pass, BuildContext context) async {
    setLoading(true);
    var client = http.Client();
    try {
      var response = await client.post(
        Uri.parse(loginAPI),
        headers: {
          "Content-Type": "application/json"
        },
        body: json.encode({
          "email": mail,
          "password": pass
        }),
      );

      if (response.statusCode == 200) {
        var jsonResponse = jsonDecode(response.body);
        var account = Account.fromJson(jsonResponse);
        Provider.of<AccountProvider>(context, listen: false).setAccount(account);
        Navigator.pushNamed(context, '/main');
      }
      else if (response.statusCode == 401) {
        // Account with the given email and password does not exist
        SnackBarShowError(context, "Invalid Email Or Password!");
      }
    } catch (e) {
      SnackBarShowError(context, "Switch To A Different IP Or A Different WiFi!");
      // Display a popup "Switch to a different IP or a different WiFi" for the user
      print(e);
    } finally {
      setLoading(false);
      client.close();
    }
  }
}