import 'package:flutter/material.dart';
import 'package:test_flutter/Account.dart';
import 'package:test_flutter/Import.dart';
import 'package:test_flutter/Itemdata.dart';
import 'package:test_flutter/Inventory.dart';
import 'package:test_flutter/Location.dart';
import 'package:test_flutter/QC.dart';
import 'package:test_flutter/service/inventory.dart';
import 'package:test_flutter/Customer_Management/CustomerUI.dart';
import 'package:test_flutter/screnn/Account.dart';
import 'package:test_flutter/screnn/Import.dart';
import 'package:test_flutter/screnn/Inventory.dart';
import 'package:test_flutter/screnn/Itemdata.dart';
import 'package:test_flutter/screnn/QC.dart';
import 'package:test_flutter/screnn/WarehousePage.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Warehouses',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  List dataFake = [
    "Accounts",
    "Import Item",
    "Item Data",
    "Item Master",
    "Warehouse",
    "QC",
    "Customers"
  ];

  List<Color> dataFakeColor = [
    Color(0xffffcf2f),
    Color(0xff6fe08d),
    Color(0xff61bdfd),
    Color(0xfffc7f7f),
    Color(0xffc884fb),
    Color(0xff78e667),
    Color(0xff867f7f)
  ];

  List<Icon> dataFakeIcon = [
    Icon(Icons.manage_accounts, color: Colors.white, size: 30),
    Icon(Icons.import_export, color: Colors.white, size: 30),
    Icon(Icons.data_thresholding, color: Colors.white, size: 30),
    Icon(Icons.mark_as_unread, color: Colors.white, size: 30),
    Icon(Icons.location_on, color: Colors.white, size: 30),
    Icon(Icons.check_box_rounded, color: Colors.white, size: 30),
    Icon(Icons.person, color: Colors.white, size: 30),
  ];

  List<Widget> pages = [
    const Account(),
    const Import(),
    const Itemdata(),
    const InventoryPage(),
    const WarehousePage(),
    const QC(),
    const CustomerUI(),
  ];

  List<Widget> correspondingPages = [
    const Account(),
    const Import(),
    const Itemdata(),
    const Itemmaster(),
    const Location(),
    const QC(),
    const CustomerUI(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Container(
            padding: EdgeInsets.only(top: 15, left: 15, right: 15, bottom: 10),
            decoration: BoxDecoration(
              color: Color(0xff674AEF),
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(20),
                bottomRight: Radius.circular(20),
              ),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Builder(
                      builder: (BuildContext context) {
                        return InkWell(
                          onTap: () {
                            Scaffold.of(context).openDrawer();
                          },
                          child: const Icon(
                            Icons.dashboard,
                            size: 30,
                            color: Colors.white,
                          ),
                        );
                      },
                    ),
                  ],
                ),
                SizedBox(height: 20),
                Padding(
                  padding: EdgeInsets.only(left: 3, bottom: 15),
                  child: Text(
                    "Trong Nguyen",
                    style: TextStyle(
                      fontSize: 25,
                      fontWeight: FontWeight.w600,
                      letterSpacing: 1,
                      wordSpacing: 2,
                      color: Colors.white,
                    ),
                  ),
                ),
                Container(
                  margin: EdgeInsets.only(top: 5, bottom: 20),
                  width: MediaQuery.of(context).size.width,
                  height: 55,
                  alignment: Alignment.center,
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(10),
                  ),
                  child: TextFormField(
                    decoration: InputDecoration(
                      border: InputBorder.none,
                      hintText: "Search here...",
                      hintStyle: TextStyle(
                        color: Colors.black.withOpacity(0.5),
                      ),
                      prefixIcon: Icon(
                        Icons.search,
                        size: 25,
                      ),
                    ),
                  ),
                )
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.only(
              top: 20,
              left: 15,
              right: 15,
            ),
            child: Column(
              children: [
                Container(
                  height: 250,
                  child: PageView.builder(
                    itemCount: (dataFake.length / 6).ceil(),
                    itemBuilder: (context, pageIndex) {
                      final startIndex = pageIndex * 6;
                      final endIndex = (pageIndex + 1) * 6;

                      return GridView.builder(
                        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: 3,
                          childAspectRatio: 1.1,
                        ),
                        itemCount: endIndex <= dataFake.length
                            ? 6
                            : dataFake.length - startIndex,
                        itemBuilder: (context, index) {
                          final dataIndex = startIndex + index;

                          return GestureDetector(
                            onTap: () {
                              final selectedPage = correspondingPages[dataIndex];
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => selectedPage),
                              );
                            },
                            child: Column(
                              children: [
                                Container(
                                  height: 60,
                                  width: 60,
                                  decoration: BoxDecoration(
                                    color: dataFakeColor[dataIndex],
                                    shape: BoxShape.circle,
                                  ),
                                  child: Center(
                                    child: Center(
                                      child: dataFakeIcon[dataIndex],
                                    ),
                                  ),
                                ),
                                SizedBox(height: 10),
                                Text(
                                  dataFake[dataIndex],
                                  style: TextStyle(
                                    fontSize: 16,
                                    fontWeight: FontWeight.w500,
                                    color: Colors.black.withOpacity(0.7),
                                  ),
                                ),
                              ],
                            ),
                          );
                        },
                      );
                    },
                  ),
                ),
                SizedBox(height: 20),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      "Warehouse",
                      style: TextStyle(
                        fontSize: 23,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    Text(
                      "See All",
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.w600,
                        color: Color(0xff674AEF),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
      drawer: Drawer(
        elevation: 0,
        width: 270,
        child: Container(
          decoration: BoxDecoration(
            borderRadius: BorderRadius.only(
              topLeft: Radius.circular(8),
              bottomLeft: Radius.circular(8),
            ),
            color: Colors.white,
          ),
          child: Column(
            children: [
              const UserAccountsDrawerHeader(
                accountName: Text("Trong Nguyen"),
                accountEmail: Text("trong@gmail.com"),
              ),
              ListTile(
                title: const Text("Help"),
                leading: const Icon(Icons.question_mark),
                onTap: () {
                  // Handle the Search Contact action here
                },
              ),
              ListTile(
                title: const Text("Feedback"),
                leading: const Icon(Icons.feedback_outlined),
                onTap: () {
                  // Handle the Search Contact action here
                },
              ),
              ListTile(
                title: const Text("System"),
                leading: const Icon(Icons.settings),
                onTap: () {
                  // Handle the Add Contact action here
                },
              ),
              ListTile(
                title: const Text("Logout"),
                leading: const Icon(Icons.logout_sharp),
                onTap: () {},
              ),
            ],
          ),
        ),
      ),
    );
  }
}
