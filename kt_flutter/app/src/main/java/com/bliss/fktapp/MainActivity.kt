package com.bliss.fktapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_native_page.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() ,View.OnClickListener{
    var sRef: WeakReference<MainActivity>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_page)
        open_native.setOnClickListener(this)
        open_flutter.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var map = mapOf(
            "name" to "Bliss",
            "form" to "android"
        )
        when(v){
            //Android内部跳转
            open_native -> openPageByUrl(this, NATIVE_PAGE_URL,map)
            //Android携参数跳转flutter
            open_flutter -> openPageByUrl(this, FLUTTER_PAGE_URL,map)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sRef!!.clear();
        sRef = null;
    }


}
