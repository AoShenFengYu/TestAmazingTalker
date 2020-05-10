package com.aoshenfengyu.testamazingtalker.base

import android.app.Application
import com.aoshenfengyu.testamazingtalker.request.RequestManager

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RequestManager.init(this)
    }
}