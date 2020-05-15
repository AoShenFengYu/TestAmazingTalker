package com.aoshenfengyu.testamazingtalker.base

import androidx.multidex.MultiDexApplication
import com.aoshenfengyu.testamazingtalker.request.RequestManager

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        RequestManager.init(this)
    }
}