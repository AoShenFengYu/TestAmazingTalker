package com.aoshenfengyu.testamazingtalker.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<V, T : BasePresenter<V>> : Fragment() {
    protected var presenter: T? = null

    abstract fun onCreatePresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter?.attachView(this@BaseFragment as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }
}