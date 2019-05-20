import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = const MethodChannel('vanethos.com/experiments');

var stuff;

void main() {
  stuff = "cenas";
  print("I was here!!!!!");
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              stuff,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: exitActivity,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  Future<void> exitActivity() async {
    try {
      await platform.invokeMethod('exitActivity', <String, dynamic>{
                 'test': "cenas",
               });
    } on PlatformException catch (e) {
      print("Error: $e");
    }
  }
}
