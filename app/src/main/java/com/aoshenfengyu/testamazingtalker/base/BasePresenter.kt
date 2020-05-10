package com.aoshenfengyu.testamazingtalker.base

abstract class BasePresenter<V> {
    protected var view: V? = null

    fun bind(view: V?) {
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

}