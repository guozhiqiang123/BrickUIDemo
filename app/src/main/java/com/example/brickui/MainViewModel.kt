package com.example.brickui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.seewo.brick.ktx.live

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val addChild: LiveData<AddViewModel> = null.live

    val counter = 0.live

}