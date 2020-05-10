package com.aoshenfengyu.testamazingtalker.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity() {
    protected var presenter: T? = null

    abstract fun onCreatePresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter?.bind(this@BaseActivity as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.unbind()
    }
}