import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/UserProvider.dart';

class AccountMain extends StatelessWidget {
  const AccountMain({Key? key}) : super(key: key);

  AppBar _buildAppBar(BuildContext context) {
    TextEditingController _searchController = TextEditingController();

    return AppBar(
      title: const Text("Accounts Page"),
      bottom: PreferredSize(
        preferredSize: Size.fromHeight(40.0),
        child: Padding(
          padding: EdgeInsets.only(bottom: 15),
          child: Container(
            width: 320,
            height: 50,
            decoration: BoxDecoration(
              color: Colors.white54,
              borderRadius: BorderRadius.all(Radius.circular(50)),
            ),
            child: Consumer<UserProvider>(
              builder: (context, userProvider, _) {
                return Center(
                  child: TextFormField(
                    controller: _searchController,
                    decoration: InputDecoration(
                      border: InputBorder.none,
                      hintText: 'Search...',
                      prefixIcon: InkWell(
                        child: Icon(Icons.search),
                      ),
                      suffixIcon: InkWell(
                        child: Icon(Icons.clear),
                        onTap: () {
                          _searchController.clear();
                          userProvider.getUsers();
                        },
                      ),
                    ),
                    onChanged: (value) {
                      userProvider.searchUsers(value);
                    },
                  ),
                );
              },
            ),
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(kToolbarHeight + 70),
        child: _buildAppBar(context),
      ),
      body: Column(
        children: [
          Expanded(
            child: FutureBuilder(
              future:
              Provider.of<UserProvider>(context, listen: false).getUsers(),
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return Center(child: CircularProgressIndicator());
                } else if (snapshot.error != null) {
                  return Center(child: Text('An Error Occurred!'));
                } else {
                  return Consumer<UserProvider>(
                    builder: (ctx, userProvider, _) {
                      final users = userProvider.users;
                      if (users == null) {
                        return Center(child: Text('No Account Found!'));
                      } else {
                        return ListView.builder(
                          padding: EdgeInsets.all(15),
                          itemCount: users.length,
                          itemBuilder: (context, index) {
                            final u = users[index];
                            return Container(
                              margin: EdgeInsets.only(bottom: 16.0),
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(8.0),
                                border: Border.all(
                                  color: Colors.transparent,
                                ),
                              ),
                              child: Card(
                                shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(30.0),
                                ),
                                elevation: 20.0,
                                color: Colors.white60,
                                child: Padding(
                                  padding: EdgeInsets.all(16.0),
                                  child: Column(
                                    crossAxisAlignment:
                                    CrossAxisAlignment.start,
                                    children: [
                                      Text(
                                        '${u.name}',
                                        style: TextStyle(
                                          fontSize: 18.0,
                                          fontWeight: FontWeight.bold,
                                        ),
                                      ),
                                      SizedBox(height: 8.0),
                                      Text('Code: ${u.code}', style: TextStyle(fontWeight: FontWeight.bold)),
                                      Text('Email: ${u.email}', style: TextStyle(fontWeight: FontWeight.bold)),
                                      Text('Phone: ${u.phone}', style: TextStyle(fontWeight: FontWeight.bold)),
                                      Text(
                                          'Roles: ${u.accountCode?.split(' | ').map((code) => userProvider.accountCodeMap[code.trim()]?.join(', ')).join(', ')}', style: TextStyle(fontWeight: FontWeight.bold)),
                                    ],
                                  ),
                                ),
                              ),
                            );
                          },
                        );
                      }
                    },
                  );
                }
              },
            ),
          ),
        ],
      ),
    );
  }
}
