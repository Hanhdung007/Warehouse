// import 'package:flutter/material.dart';
// import 'package:test_flutter/screnn/ViewDetailsItem.dart';
// import 'package:test_flutter/service/inventory.dart';
//
//
// import '../model/Itemmaster.dart';
//
// class InventoryPage extends StatefulWidget {
//   const InventoryPage({super.key});
//
//   @override
//   State<StatefulWidget> createState() => _InventoryPageState();
//
// }
//
// class _InventoryPageState extends State<InventoryPage> {
//   late List<Itemmaster> list = [];
//
//   @override
//   void initState() {
//     // TODO: implement initState
//     super.initState();
//     getdata();
//   }
//
//   void getdata() async {
//     list = await Inventory.getInventory();
//     Future.delayed(const Duration(seconds : 2)).then((value) => setState( () {}));
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: const Text("Inventory List"),
//       ),
//       body: Container(
//         padding: const EdgeInsets.all(15),
//         child: FutureBuilder(
//           future: Inventory.getInventory(),
//           builder: (BuildContext context, AsyncSnapshot snapshot){
//             if(snapshot.connectionState == ConnectionState.waiting){
//               return Container(
//                 child: const Center(child: CircularProgressIndicator())
//               );
//             }else{
//               return ListView.builder(
//                   padding: EdgeInsets.all(15),
//                   itemCount: list.length,
//                   itemBuilder: (context, index) {
//                     final item = list[index];
//                     //
//                     return Card(
//                         child: ListTile(
//                           onTap: (){
//                             Navigator.push(
//                                 context,
//                                 MaterialPageRoute(
//                                     builder: (context) =>
//                                         ViewDetails(item: list[index])));
//                           },
//                             leading: Text(item.id.toString()),
//                             title: Text('${item.itemName}'), // Hiển thị tên hàng
//                             subtitle: Text('Location: ${item.locationName} || Import Date: ${item.dateImport} || Remain: ${item.qcAcceptQuantity.toString()}/${item.quantity.toString()}'),
//                             // trailing: Text(''),
//                         )
//                     );
//                   });
//             }
//           },
//         )
//       )
//     );
//   }
// }

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_flutter/service/inventory.dart';

import 'ViewDetailsItem.dart';

class InventoryPage extends StatelessWidget {
  const InventoryPage({Key? key}) : super(key: key);

  AppBar _buildAppBar(BuildContext context) {
    TextEditingController _searchController = TextEditingController();

    return AppBar(
      title: const Text("Inventory Page"),
      bottom: PreferredSize(
        preferredSize: Size.fromHeight(40.0),
        child: Padding(
          padding: EdgeInsets.only(bottom: 15),
          child: Container(
            width: 320,
            height: 50,
            decoration: BoxDecoration(
                color: Colors.white54,
                borderRadius: BorderRadius.all(Radius.circular(50))),
            child: Consumer<Inventory>(
              builder: (context, inventoryProvider, _) {
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
                          inventoryProvider.getInventory();
                        },
                      ),
                    ),
                    onChanged: (value) {
                      inventoryProvider.searchInventory(value);
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
        body: FutureBuilder(
          future: Provider.of<Inventory>(context, listen: false).getInventory(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(child: CircularProgressIndicator());
            } else if (snapshot.error != null) {
              return Center(child: Text('An Error Occurred!'));
            } else {
              return Consumer<Inventory>(
                builder: (ctx, inventory, _) {
                  final inventories = inventory.inventory;
                  if (inventories == null) {
                    return Center(child: Text('Not Found Inventory Item!'));
                  } else {
                    return ListView.builder(
                        padding: EdgeInsets.all(15),
                        itemCount: inventories.length,
                        itemBuilder: (context, index) {
                          final u = inventories[index];
                          return Container(
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(8.0),
                              border: Border.all(
                                color: Colors.transparent,
                                // width: 2.0,
                              ),
                            ),
                            child: InkWell(
                              onTap: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => ViewDetails(
                                            item: inventories[index])));
                              },
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
                                        '${u.itemName}',
                                        style: TextStyle(
                                          fontSize: 18.0,
                                          fontWeight: FontWeight.bold,
                                        ),
                                      ),
                                      SizedBox(height: 8.0),
                                      Text('Id: ${u.id}'),
                                      Text('Location: ${u.locationName}'),
                                      Text('Date: ${u.dateImport}'),
                                      Text(
                                          'Remain: ${u.qcAcceptQuantity.toString()}/${u.quantity.toString()}'),
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          );
                        });
                  }
                },
              );
            }
          },
        ));
  }
}
