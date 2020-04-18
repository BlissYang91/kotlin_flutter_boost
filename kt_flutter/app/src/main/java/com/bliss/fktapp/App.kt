package com.bliss.fktapp

import android.app.Application
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoost.BoostLifecycleListener
import com.idlefish.flutterboost.Utils
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.embedding.android.FlutterView
import io.flutter.view.FlutterMain


/**
 * @Author yangtianfu
 * @CreateTime 2020/4/17 19:45
 * @Describe
 */
class App :Application() {
    override fun onCreate() {
        super.onCreate()
        initFlutterBoost()
    }

    /**
     * FlutterBoost初始化
     * 无论是 Flutter 之间路由跳转还是 Flutter 与 Native 之间路由跳转均需要通过 INativeRouter 接口进行交互；
     */
    private fun initFlutterBoost() {

        val router =
            INativeRouter { context, url, urlParams, requestCode, exts ->
                val assembleUrl: String = Utils.assembleUrl(url, urlParams)
                openPageByUrl(context, assembleUrl, urlParams)
            }

        val boostLifecycleListener: BoostLifecycleListener = object : BoostLifecycleListener {
            override fun onEngineCreated() {}
            override fun onPluginsRegistered() {}
            override fun beforeCreateEngine() {}
            override fun onEngineDestroy() {}
        }
        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //

        val platform = FlutterBoost.ConfigBuilder(this, router)
            .isDebug(true)
            .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
            .renderMode(FlutterView.RenderMode.texture)
            .lifecycleListener(boostLifecycleListener)
            .build()
        FlutterBoost.instance().init(platform)
    }
}