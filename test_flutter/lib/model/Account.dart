class Account {
  String? getName;
  bool? error;
  String? message;
  String? username;

  Account({this.getName, this.error, this.message, this.username});

  Account.fromJson(Map<String, dynamic> json) {
    getName = json['getName'];
    error = json['error'];
    message = json['message'];
    username = json['username'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['getName'] = this.getName;
    data['error'] = this.error;
    data['message'] = this.message;
    data['username'] = this.username;
    return data;
  }
}
