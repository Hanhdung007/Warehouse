import 'package:flutter/material.dart';

class Itemmaster extends StatefulWidget {
  const Itemmaster({super.key});

  @override
  State<StatefulWidget> createState() => _ItemmasterState();
}

class _ItemmasterState extends State<Itemmaster> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Item Master Page"),
      ),
    );
  }
}
