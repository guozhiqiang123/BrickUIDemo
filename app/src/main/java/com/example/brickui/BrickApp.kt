package com.example.brickui

import android.app.Application
import com.drake.net.NetConfig
import com.drake.net.okhttp.setConverter
import com.example.brickui.http.mock.Api
import com.example.brickui.http.mock.MockDispatcher
import com.example.brickui.http.mock.SerializationConverter

class BrickApp : Application() {
    override fun onCreate() {
        super.onCreate()

        NetConfig.initialize(Api.HOST, this) {
            // 数据转换器
            setConverter(SerializationConverter())
        }

        MockDispatcher.initialize()
    }

    companion object {
        const val TAG = "BrickApp"
    }
}