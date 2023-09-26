import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_flutter/providers/AccountProvider.dart';
import 'package:test_flutter/providers/ImportProvider.dart';
import 'package:test_flutter/providers/LoginProvider.dart';
import 'package:test_flutter/providers/UserProvider.dart';
import 'package:test_flutter/screnn/AccountMain.dart';
import 'package:test_flutter/screnn/CustomerUI.dart';
import 'package:test_flutter/screnn/Import.dart';
import 'package:test_flutter/screnn/Inventory.dart';
import 'package:test_flutter/screnn/Login.dart';
import 'package:test_flutter/screnn/MainUI.dart';
import 'package:test_flutter/screnn/ViewDetailsItem.dart';
import 'package:test_flutter/service/inventory.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(
            create: (context) => LoginProvider(),
          ),
          ChangeNotifierProvider(
            create: (context) => AccountProvider(),
          ),
          ChangeNotifierProvider(
            create: (context) => UserProvider(),
          ),
          ChangeNotifierProvider(
            create: (context) => ImportProvider(),
          ),
          ChangeNotifierProvider(
            create: (context) => Inventory(),
          ),
          // ChangeNotifierProvider(
          //   create: (context) => CartProvider(),
          // ),
        ],
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          theme: ThemeData.from(
            colorScheme: ColorScheme.fromSeed(
              seedColor: const Color.fromRGBO(112, 86, 238, 100).withOpacity(
                  0.1),
            ),
          ),
          initialRoute: '/login',
          routes: {
            '/login': (context) => const Login(),
            '/main': (context) => const MainUI(),
            '/account': (context) => const AccountMain(),
            '/inventory': (context) => const InventoryPage(),
            '/import': (context) => const Import(),
            '/customer': (context) => const CustomerUI(),
            // '/detailItem': (context) => const ViewDetails(),
          },
        ));
  }
}