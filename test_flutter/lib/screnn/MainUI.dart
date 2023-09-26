import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_flutter/providers/AccountProvider.dart';
import 'package:test_flutter/model/Account.dart';

import '../Location.dart';
import 'AccountMain.dart';
import 'CustomerUI.dart';
import 'Import.dart';
import 'Inventory.dart';
import 'Itemdata.dart';

class MainUI extends StatefulWidget {
  const MainUI({super.key});

  @override
  State<StatefulWidget> createState() => _MainUIState();
}

class _MainUIState extends State<MainUI> {
  late Account account;

  List dataFake = [
    "Accounts Data",
    "Import Order",
    "Item Data",
    "Inventory Data",
    "Customer Data",
  ];

  List<Color> dataFakeColor = [
    const Color(0xffffcf2f),
    const Color(0xff6fe08d),
    const Color(0xff61bdfd),
    const Color(0xfffc7f7f),
    const Color(0xffc884fb),
  ];

  List<Icon> dataFakeIcon = [
    Icon(Icons.manage_accounts, color: Colors.white, size: 30),
    Icon(Icons.import_export, color: Colors.white, size: 30),
    Icon(Icons.data_thresholding, color: Colors.white, size: 30),
    Icon(Icons.inventory, color: Colors.white, size: 30),
    Icon(Icons.emoji_people, color: Colors.white, size: 30),
  ];

  List<Widget> pages = [
    const AccountMain(),
    const Import(),
    const Itemdata(),
    const InventoryPage(),
    const CustomerUI(),
  ];

  @override
  Widget build(BuildContext context) {
    var accountProvider = Provider.of<AccountProvider>(context);
    var account1 = accountProvider.account;

    return Scaffold(
      body: ListView(
        children: [
          Container(
            padding: EdgeInsets.only(top: 15, left: 15, right: 15, bottom: 10),
            decoration: BoxDecoration(
                color: Color(0xff674AEF),
                borderRadius: BorderRadius.only(
                  bottomLeft: Radius.circular(20),
                  bottomRight: Radius.circular(20),
                )),
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
                    account1?.getName ?? '',
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
                GridView.builder(
                  itemCount: dataFake.length,
                  shrinkWrap: true,
                  physics: NeverScrollableScrollPhysics(),
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 3,
                    childAspectRatio: 1.1,
                  ),
                  itemBuilder: (context, index) {
                    return GestureDetector(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => pages[index]),
                        );
                      },
                      child: Column(
                        children: [
                          Container(
                            height: 60,
                            width: 60,
                            decoration: BoxDecoration(
                                color: dataFakeColor[index],
                                shape: BoxShape.circle),
                            child: Center(
                              child: Center(
                                child: dataFakeIcon[index],
                              ),
                            ),
                          ),
                          SizedBox(height: 10),
                          Text(
                            dataFake[index],
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
                ),
                SizedBox(height: 20),
              ],
            ),
          )
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
              UserAccountsDrawerHeader(
                accountName: Text(account1?.getName ?? '',),
                accountEmail: Text(account1?.username ?? '',),
              ),
              ListTile(
                title: const Text("Help"),
                leading: const Icon(Icons.question_mark),
                onTap: () {
                  // Xử lý hành động tìm kiếm liên hệ ở đây
                },
              ),
              ListTile(
                title: const Text("Feedback"),
                leading: const Icon(Icons.feedback_outlined),
                onTap: () {
                  // Xử lý hành động phản hồi ở đây
                },
              ),
              ListTile(
                title: const Text("System"),
                leading: const Icon(Icons.settings),
                onTap: () {
                  // Xử lý hành động thêm liên hệ ở đây
                },
              ),
              ListTile(
                title: const Text("Logout"),
                leading: const Icon(Icons.logout_sharp),
                onTap: () {
                  Provider.of<AccountProvider>(context, listen: false).logOut(context);
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
