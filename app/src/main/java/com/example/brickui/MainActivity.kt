package com.example.brickui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seewo.brick.ktx.setStatusBarTransparent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarTransparent(true)
        setContentView(contentView())
    }

    fun showDrawer() {
        Toast.makeText(this, "正在打开抽屉布局", Toast.LENGTH_SHORT).show()
    }
}