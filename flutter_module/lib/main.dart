import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'sample_page_widgets.dart';


void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }

}

class MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    FlutterBoost.singleton.registerPageBuilders({
//      将flutter中的每个widget以键值对的形式注册进FlutterBoost
      /// 原生端可通过 'flutterPage'这个key去路由到指定Widget页面FlutterRouteWidget
      'flutterPage': (pageName, params, _) {
        print("flutterPage params:$params");
        return FlutterRouteWidget(params:params);
      },
    });
    FlutterBoost.singleton.addBoostNavigatorObserver(TestBoostNavigatorObserver());
  }



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Boost 测试',
      builder: FlutterBoost.init(postPush: _onRoutePushed),
      home: Container(
        color: Colors.white,
      ),
    );
  }

  void _onRoutePushed(
      String pageName, String uniqueId, Map params, Route route, Future _) {
  }

}
class TestBoostNavigatorObserver extends NavigatorObserver{
  void didPush(Route<dynamic> route, Route<dynamic> previousRoute) {

    print("flutterboost#didPush");
  }

  void didPop(Route<dynamic> route, Route<dynamic> previousRoute) {
    print("flutterboost#didPop");
  }

  void didRemove(Route<dynamic> route, Route<dynamic> previousRoute) {
    print("flutterboost#didRemove");
  }

  void didReplace({Route<dynamic> newRoute, Route<dynamic> oldRoute}) {
    print("flutterboost#didReplace");
  }
}

