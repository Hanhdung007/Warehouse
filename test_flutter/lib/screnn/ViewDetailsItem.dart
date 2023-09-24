import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../model/Itemmaster.dart';

class ViewDetails extends StatefulWidget {
  const ViewDetails({super.key, required this.item});

  final Itemmaster item;
  final String img_url = "http://192.168.1.11:9999/";

  @override
  State<ViewDetails> createState() => _ViewDetailsState();
}

class _ViewDetailsState extends State<ViewDetails> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Item Details"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            SizedBox(height: 10, width: 10),
            Align(
                alignment: Alignment.center,
                child: Image.network(
                    "http://192.168.1.11:9999/" + widget.item.image,
                    fit: BoxFit.cover,
                    height: 150.0,
                    width: 150.0)),
            SizedBox(height: 10),
            Row(children: [
              Text(
                widget.item.itemName ?? "",
                style: TextStyle(fontSize: 30, fontWeight: FontWeight.w600),
              ),
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                "-Item code: ",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
              Text(widget.item.id.toString() ?? "",
                  style: TextStyle(fontSize: 20))
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text("-Location: ",
                  style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600)),
              Text(
                widget.item.locationName ?? "",
                style: TextStyle(fontSize: 20),
              ),
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                "-Recieve Date: ",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
              Text(widget.item.dateImport.toString() ?? "",
                  style: TextStyle(fontSize: 20))
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                "-Total Quantity: ",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
              Text(widget.item.quantity.toString() ?? '',
                  style: TextStyle(fontSize: 20))
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                "-Quantity Accept: ",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
              Text(widget.item.qcAcceptQuantity.toString(),
                  style: TextStyle(fontSize: 20))
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                "-Quantity Injection: ",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
              Text(widget.item.qcInjectQuantity.toString(),
                  style: TextStyle(fontSize: 20))
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                "-Quantity Injection: ",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
              Text(widget.item.qcInjectQuantity.toString(),
                  style: TextStyle(fontSize: 20))
            ]),
            SizedBox(height: 10),
            Row(children: [
              Text(
                  widget.item.pass.toString() == 'true' ? 'Active' : 'Inactive',
                  style: TextStyle(
                      fontSize: 20,
                      color: widget.item.pass.toString() == 'true'
                          ? Colors.teal
                          : Colors.redAccent,
                      fontWeight: FontWeight.w600))
            ]),
          ],
        ),
      ),
    );
  }
}
