class User {
  String? code;
  String? name;
  String? email;
  Null? password;
  String? phone;
  bool? isActive;
  String? accountCode;
  Null? roleId;

  User(
      {this.code,
        this.name,
        this.email,
        this.password,
        this.phone,
        this.isActive,
        this.accountCode,
        this.roleId});

  User.fromJson(Map<String, dynamic> json) {
    code = json['code'];
    name = json['name'];
    email = json['email'];
    password = json['password'];
    phone = json['phone'];
    isActive = json['isActive'];
    accountCode = json['accountCode'];
    roleId = json['roleId'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['code'] = this.code;
    data['name'] = this.name;
    data['email'] = this.email;
    data['password'] = this.password;
    data['phone'] = this.phone;
    data['isActive'] = this.isActive;
    data['accountCode'] = this.accountCode;
    data['roleId'] = this.roleId;
    return data;
  }
}
