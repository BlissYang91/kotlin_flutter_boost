import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
// 原生路由至此页面
class FlutterRouteWidget extends StatefulWidget {
  final Map params;
  final String message;

  //可选参数构造函数
  FlutterRouteWidget({this.params,this.message});

  @override
  _FlutterRouteWidgetState createState()=>_FlutterRouteWidgetState();

}

class _FlutterRouteWidgetState extends State<FlutterRouteWidget>{
  final TextEditingController _usernameController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    //获取原生传来的参数信息
    final String message=widget.message;

    return Scaffold(
      appBar: AppBar(
        brightness: Brightness.light,
        backgroundColor: Colors.white,
        textTheme: new TextTheme(title: TextStyle(color: Colors.black)),
        title: Text('Flutter Boost'),
      ),
      body: SingleChildScrollView(
        child: Container(
          margin: const EdgeInsets.all(24.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                margin: const EdgeInsets.only(top: 10.0,bottom: 20.0),
                alignment: AlignmentDirectional.center,
                child: Text(
                  message ?? "flutter页面接收到Android参数\n :${widget.params}",
                  style: TextStyle(fontSize: 20.0,color: Colors.blue),
                ),
              ),
              const CupertinoTextField(
                prefix: Icon(
                  CupertinoIcons.person_solid,
                  color: CupertinoColors.lightBackgroundGray,
                  size: 28.0,
                ),
                padding: EdgeInsets.symmetric(horizontal: 6.0,vertical: 12.0),
                clearButtonMode: OverlayVisibilityMode.editing,
                textCapitalization: TextCapitalization.words,
                autocorrect: false,
                decoration: BoxDecoration(
                  border: Border(bottom: BorderSide(width: 0.0,color: CupertinoColors.inactiveGray))
                ),
                placeholder: 'Name',
              ),
              InkWell(
                child:  Container(
                  padding: const EdgeInsets.all(8.0),
                  margin: const EdgeInsets.all(8.0),
                  color: Colors.yellow,
                  child: Text(
                    'Flutter带参数跳转原生',
                    style: TextStyle(fontSize: 22.0, color: Colors.black),
                  ),
                ),
                onTap: ()=> FlutterBoost.singleton.open("sample://nativePage",urlParams:  <dynamic,dynamic>{
                  "query": {"from": "\n Come From Flutter"}
                }),
              ),
              InkWell(
                child: Container(
                    padding: const EdgeInsets.all(8.0),
                    margin: const EdgeInsets.all(8.0),
                    color: Colors.yellow,
                    child: Text(
                      'Flutter 跳转 Flutter',
                      style: TextStyle(fontSize: 22.0, color: Colors.black),
                    )),
                onTap: () {
                  Navigator.push<dynamic>(context,
                      MaterialPageRoute<dynamic>(builder: (_) => PushWidget()));
                },
              ),

            ],
          ),
        ),
      ),
    );
  }
}

class PushWidget extends StatefulWidget{
  @override
  _PushWidgetState createState() => _PushWidgetState();
  }

class _PushWidgetState extends State<PushWidget>{
  VoidCallback _backPressedListenerUnsub;

  @override
  Widget build(BuildContext context) {
    return FlutterRouteWidget(message:"Flutter 有状态 Widget");
  }

  @override
  void dispose() {
    print('[XDEBUG] - PushWidget is disposing~');
    super.dispose();
    _backPressedListenerUnsub?.call();
  }
}


