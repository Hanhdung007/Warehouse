import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_flutter/providers/ImportProvider.dart';

class Import extends StatelessWidget {
  const Import({Key? key}) : super(key: key);

  AppBar _buildAppBar(BuildContext context) {
    TextEditingController _searchController = TextEditingController();

    return AppBar(
      title: const Text("Import Page"),
      bottom: PreferredSize(
        preferredSize: Size.fromHeight(40.0),
        child: Padding(
          padding: EdgeInsets.only(bottom: 15),
          child: Container(
            width: 320,
            height: 50,
            decoration: BoxDecoration(
                color: Colors.white54,
                borderRadius: BorderRadius.all(Radius.circular(50))),
            child: Consumer<ImportProvider>(
              builder: (context, importProvider, _) {
                return Center(
                  child: TextFormField(
                    controller: _searchController,
                    decoration: InputDecoration(
                      border: InputBorder.none,
                      hintText: 'Search...',
                      prefixIcon: InkWell(
                        child: Icon(Icons.search),
                      ),
                      suffixIcon: InkWell(
                        child: Icon(Icons.clear),
                        onTap: () {
                          _searchController.clear();
                          importProvider.getImports();
                        },
                      ),
                    ),
                    onChanged: (value) {
                      importProvider.searchImports(value);
                    },
                  ),
                );
              },
            ),
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(kToolbarHeight + 70),
        child: _buildAppBar(context),
      ),
      body: FutureBuilder(
          future:
          Provider.of<ImportProvider>(context, listen: false).getImports(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(child: CircularProgressIndicator());
            } else if (snapshot.error != null) {
              return Center(child: Text('An Error Occurred!'));
            } else {
              return Consumer<ImportProvider>(
                  builder: (ctx, importProvider, _) {
                    final imports = importProvider.imports;
                    if (imports == null) {
                      return Center(child: Text('Not Found Import Item!'));
                    } else {
                      return ListView.builder(
                          padding: EdgeInsets.all(15),
                          itemCount: imports.length,
                          itemBuilder: (context, index) {
                            final u = imports[index];
                            return Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(8.0),
                                border: Border.all(
                                  color: Colors.transparent,
                                  // width: 2.0,
                                ),
                              ),
                              child: Card(
                                shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(30.0),
                                ),
                                elevation: 20.0,
                                color: Colors.white60,
                                child: Padding(
                                  padding: EdgeInsets.all(16.0),
                                  child: Column(
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      Text(
                                        '${u.driver}',
                                        style: TextStyle(
                                          fontSize: 18.0,
                                          fontWeight: FontWeight.bold,
                                        ),
                                      ),
                                      SizedBox(height: 8.0),
                                      Text('Phone: ${u.driversPhone}'),
                                      Text('Date Import: ${u.dateImport}'),
                                      Text('Note: ${u.note}'),
                                      Text('Status: ${u.status}'),
                                      Text('Supplier Name: ${u.supplierName}'),
                                      // Add more fields here as needed
                                    ],
                                  ),
                                ),
                              ),
                            );
                          });
                    }
                  });
            }
          }),
    );
  }
}
