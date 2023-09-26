import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:test_flutter/constaints/urlAPI.dart';

import '../model/Importorders.dart';

class ImportProvider with ChangeNotifier{
  late List<Importorders> _imports = [];
  late List<Importorders> _filteredImports = [];

  List<Importorders> get imports => _filteredImports.isNotEmpty ? _filteredImports : _imports;

  void searchImports(String searchImport) {
    if (searchImport.isNotEmpty) {
      _filteredImports = _imports.where((import) {
        return import.driver!.toLowerCase().contains(searchImport.toLowerCase()) ||
            import.driversPhone!.toLowerCase().contains(searchImport.toLowerCase()) ||
            import.dateImport!.toLowerCase().contains(searchImport.toLowerCase()) ||
            import.note!.toLowerCase().contains(searchImport.toLowerCase()) ||
            (import.status == 1 ? 'true' : 'false') == searchImport ||
            import.supplierName!.toLowerCase().contains(searchImport.toLowerCase());
      }).toList();
    } else {
      _filteredImports = [];
    }
    notifyListeners();
  }

  Future<void> getImports() async {
    final response = await http.get(Uri.parse(importAPI));
    if (response.statusCode == 200) {
      final jsonData = await json.decode(response.body);
      _imports = List<Importorders>.from(jsonData.map((data) => Importorders.fromJson(data)));

      _filteredImports = _imports;
      notifyListeners();
    } else {
      throw Exception('Failed To Load Import Item!');
    }
  }
}