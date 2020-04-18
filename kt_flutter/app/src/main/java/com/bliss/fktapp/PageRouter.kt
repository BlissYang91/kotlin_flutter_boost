package com.bliss.fktapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.idlefish.flutterboost.containers.BoostFlutterActivity

/**
 * @Author yangtianfu
 * @CreateTime 2020/4/17 20:39
 * @Describe 是我们原生定义顶层的一个页面路由类 处理Flutter页面根据url打开另一个页面
 */
// flutter 跳转Android的URL
val NATIVE_PAGE_URL = "sample://nativePage"
//  android跳转flutter的URL
val FLUTTER_PAGE_URL = "sample://flutterPage"
//只读map集合存放路由名字
val pageName = mapOf(
    "sample://flutterPage" to "flutterPage"
)

fun openPageByUrl(
    context: Context,
    url: String,
    params: Map<*, *>?
): Boolean {
    return openPageByUrl(context, url, params, 0)
}

fun openPageByUrl(
    context: Context,
    url: String,
    params: Map<*, *>?,
    requestCode: Int
): Boolean {
    val path = url.split("\\?").toTypedArray()[0]
//    从flutter过来的页面会走这里去匹配跳转对应的activity，同时携带必要参数
//    openPageByUrl ==: sample://nativePage?from=Flutter
    Log.i("openPageByUrl == ", path)
    return try {
        if (pageName.containsKey(path)) {
            Log.i("路由至Flutter页面 == ", path)
            val intent =
                BoostFlutterActivity.withNewEngine().url(pageName[path]!!).params(params!!)
                    .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context)
            if (context is Activity) {
                context.startActivityForResult(intent, requestCode)
            } else {
                context.startActivity(intent)
            }
            return true
        } else if (url.startsWith(NATIVE_PAGE_URL)) {
            if (url.contains("?")){
                //  从Flutter过来的页面携带参数
                var intent = Intent(context, NativePageActivity::class.java)
                   intent.putExtra("url",url)
                context.startActivity(intent)
            }else{
//                从Flutter过来的页面没有携带参数
                context.startActivity(Intent(context, NativePageActivity::class.java))
            }
            return true
        } else{
            Log.i("路由未知页面 == ", path)
            Log.e("key", pageName.keys.toString())
        }
        false
    } catch (t: Throwable) {
        false
    }
}

