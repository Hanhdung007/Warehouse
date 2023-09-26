class Importorders {
  int? id;
  String? driver;
  String? driversPhone;
  String? dateImport;
  String? note;
  bool? status;
  String? supplierName;
  Null? supId;

  Importorders(
      {this.id,
        this.driver,
        this.driversPhone,
        this.dateImport,
        this.note,
        this.status,
        this.supplierName,
        this.supId});

  Importorders.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    driver = json['driver'];
    driversPhone = json['driversPhone'];
    dateImport = json['dateImport'];
    note = json['note'];
    status = json['status'];
    supplierName = json['supplierName'];
    supId = json['supId'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['driver'] = this.driver;
    data['driversPhone'] = this.driversPhone;
    data['dateImport'] = this.dateImport;
    data['note'] = this.note;
    data['status'] = this.status;
    data['supplierName'] = this.supplierName;
    data['supId'] = this.supId;
    return data;
  }
}
