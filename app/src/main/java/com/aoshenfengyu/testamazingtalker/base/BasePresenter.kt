package com.aoshenfengyu.testamazingtalker.base

import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BasePresenter<V>() {

    val disposables = CompositeDisposable()

    fun unsubscribe() {
        disposables.dispose()
    }

}