package com.example.placeholder.common.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.disposeOnCleared() = apply {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}