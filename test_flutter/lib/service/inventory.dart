import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import '../constaints/urlAPI.dart';
import '../model/Itemmaster.dart';

class Inventory with ChangeNotifier {
  late List<Itemmaster> _inventory = [];
  late List<Itemmaster> _filteredInventory = [];

  List<Itemmaster> get inventory => _filteredInventory.isNotEmpty ? _filteredInventory : _inventory;

  Future<void> getInventory() async {
    final response = await http.get(Uri.parse(inventoryAPI));
    if (response.statusCode == 200) {
      final jsonData = await json.decode(response.body);
      _inventory = List<Itemmaster>.from(jsonData.map((data) => Itemmaster.fromJson(data)));

      _filteredInventory = _inventory;
      notifyListeners();
    } else {
      throw Exception('Failed To Load Import Item!');
    }
  }

  void searchInventory(String searchImport) {
    if (searchImport.isNotEmpty) {
      _filteredInventory = inventory.where((inventory) {
        return inventory.id!.toString().contains(searchImport.toLowerCase()) ||
            inventory.itemName!.toLowerCase().contains(searchImport.toLowerCase()) ||
            inventory.locationName!.toLowerCase().contains(searchImport.toLowerCase()) ||
            inventory.dateImport!.toLowerCase().contains(searchImport.toLowerCase()) ||
            inventory.qcAcceptQuantity!.toString().contains(searchImport.toLowerCase()) ||
            inventory.quantity!.toString().contains(searchImport.toLowerCase());
      }).toList();
    } else {
      _filteredInventory = [];
    }
    notifyListeners();
  }

  static Future<List<Itemmaster>> getSingleItemInventory(int id) async {
    var singleItemModel;
    try {
      final response = await http.get(Uri.parse(inventoryAPI));
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
