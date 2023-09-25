import 'package:http/http.dart' as http;
import 'dart:convert';

import '../model/Itemmaster.dart';

class Inventory {
  static Future<List<Itemmaster>> getInventory() async {

    String url = "http://192.168.1.11:9999/api/inventory/getdata";
    final response = await http.get(Uri.parse(url));
    var data = json.decode(response.body);
    List<Itemmaster> iList = [];
    for (var c in data) {
      Itemmaster item = Itemmaster(
        id: c['id'],
        locationName: c['locationName'],
        quantity: c['quantity'],
        recieveNo: c['recieveNo'],
        dateImport: c['dateImport'],
        note: c['note'],
        qcAcceptQuantity: c['qcAcceptQuantity'],
        qcInjectQuantity: c['qcInjectQuantity'],
        qcBy: c['qcBy'],
        idImport: c['idImport'],
        itemName: c['itemName'],
        codeItemdata: c['codeItemdata'],
        supplierName: c['supplierName'],
        image: c['image'],
        disable: c['disable'],
        pass: c['pass'],
      );

      iList.add(item);
    }
    return iList;
  }

  static Future<List<Itemmaster>> getSingleItemInventory(int id) async {
    String url = "http://192.168.1.11:9999/api/inventory/id";
    var singleItemModel;
    try {
      final response = await http.get(Uri.parse(url));
      print(response.statusCode);
      if (response.statusCode == 200) {
        var jsonString = response.body;
        var jsonMap = json.decode(jsonString);
        singleItemModel = Itemmaster.fromJson(jsonMap);
      }
    } catch (Exception) {
      return singleItemModel;
    }
    return singleItemModel;
  }
}
