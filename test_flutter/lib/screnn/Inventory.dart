import 'package:flutter/material.dart';
import 'package:test_flutter/screnn/ViewDetailsItem.dart';
import 'package:test_flutter/service/inventory.dart';


import '../model/Itemmaster.dart';

class InventoryPage extends StatefulWidget {
  const InventoryPage({super.key});

  @override
  State<StatefulWidget> createState() => _InventoryPageState();

}

class _InventoryPageState extends State<InventoryPage> {
  late List<Itemmaster> list = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getdata();
  }

  void getdata() async {
    list = await Inventory.getInventory();
    Future.delayed(const Duration(seconds : 2)).then((value) => setState( () {}));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Inventory List"),
      ),
      body: Container(
        padding: const EdgeInsets.all(15),
        child: FutureBuilder(
          future: Inventory.getInventory(),
          builder: (BuildContext context, AsyncSnapshot snapshot){
            if(snapshot.connectionState == ConnectionState.waiting){
              return Container(
                child: const Center(child: CircularProgressIndicator())
              );
            }else{
              return ListView.builder(
                  padding: EdgeInsets.all(15),
                  itemCount: list.length,
                  itemBuilder: (context, index) {
                    final item = list[index];
                    //
                    return Card(
                        child: ListTile(
                          onTap: (){
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) =>
                                        ViewDetails(item: list[index])));
                          },
                            leading: Text(item.id.toString()),
                            title: Text('${item.itemName}'), // Hiển thị tên hàng
                            subtitle: Text('Location: ${item.locationName} || Import Date: ${item.dateImport} || Remain: ${item.qcAcceptQuantity.toString()}/${item.quantity.toString()}'),
                            // trailing: Text(''),
                        )
                    );
                  });
            }
          },
        )
      )
    );
  }
}
