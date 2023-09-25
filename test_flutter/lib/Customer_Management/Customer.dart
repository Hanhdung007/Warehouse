class Customer {
  String name;
  String email;
  String phone;
  String fax;
  String address;
  bool disable;

  Customer({
    required this.name,
    required this.email,
    required this.phone,
    required this.fax,
    required this.address,
    required this.disable,
  });

  factory Customer.fromJson(Map<String, dynamic> json) {
    return Customer(
      name: json['name'],
      email: json['email'],
      phone: json['phone'],
      fax: json['fax'],
      address: json['address'],
      disable: json['disable'] ?? false,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'name': name,
      'email': email,
      'phone': phone,
      'fax': fax,
      'address': address,
      'disable': disable,
    };
  }
}
