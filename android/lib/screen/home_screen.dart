import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  // List menu = ['warehouse', 'inventory', 'abc'];
  // List<Color> colorMenu = [
  //   Color(0xFFCB84FB),
  //   Color(0xFFCB84FB),
  //   Color(0xFFCB84FB),
  // ];
  // List<Icon> menuIcon = [
  //   Icon(Icons.warehouse, color: Colors.white, size: 30),
  //   Icon(Icons.inventory_2_sharp, color: Colors.white, size: 30),
  //   Icon(Icons.soap_outlined, color: Colors.white, size: 30),
  // ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: ListView(children: [
      Container(
          padding: EdgeInsets.only(top: 10, left: 10, right: 15, bottom: 10),
          decoration: BoxDecoration(
              color: Color(0xFF28262A),
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(20),
                bottomRight: Radius.circular(20),
              )),
          child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Image(
                      image: AssetImage('images/logoDTA1.png'),
                      width: 60,
                      height: 40),
                  CircleAvatar(
                    radius: 20,
                    backgroundImage: AssetImage('images/avata.jpg'),
                  )
                ],
              ),
              SizedBox(height: 20),
              Padding(
                padding: EdgeInsets.only(left: 3, bottom: 15),
                child: Text('Hi, Programmer',
                    style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.w600,
                        letterSpacing: 1,
                        wordSpacing: 2,
                        color: Colors.white)),
              ),
            ],
          )),
      Padding(
        padding: EdgeInsets.only(top: 20, left: 15, right: 15),
        child: Column(
          children: [
            GridView.builder(
              shrinkWrap: true,
              physics: NeverScrollableScrollPhysics(),
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 3,
                // childAspectRatio: 1.1,
              ),
              itemBuilder: (context, index) {
                return Column(
                  children: [
                    Container(
                      height: 60,
                      width: 60,
                      decoration: BoxDecoration(
                          color: Color(0xFFCB84FB), shape: BoxShape.circle),
                      child: Center(
                        child: Icon(Icons.warehouse,
                            color: Colors.white, size: 30),
                      ),
                    )
                  ],
                );
              },
            ),
          ],
        ),
      )
    ]));
  }
}
