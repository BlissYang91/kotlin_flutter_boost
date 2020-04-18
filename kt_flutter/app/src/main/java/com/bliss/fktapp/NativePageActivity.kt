package com.bliss.fktapp

import android.os.Bundle
import android.view.View
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import kotlinx.android.synthetic.main.activity_native_page.*


class NativePageActivity : BoostFlutterActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_page)
        open_native.setOnClickListener(this)
        open_flutter.setOnClickListener(this)
//         获取flutter中传来的参数
        var intent = intent
        var url = intent.getStringExtra("url")
        if (url != null){
            var map = UrlUtil.parseParams(url)
            var param = map.get("from")
            if (param != null){
                tv_flutter_param.setText("接收到来自flutter的传递数据:$param")
            }
        }
    }

    override fun onClick(v: View?) {
        var map = mapOf(
            "name" to "bliss",
            "sex" to "男"
        )
        when(v){
            //Android内部跳转
            open_native -> openPageByUrl(this, NATIVE_PAGE_URL,map)
            //Android携参数跳转flutter
            open_flutter -> openPageByUrl(this, FLUTTER_PAGE_URL,map)
        }
    }

}
