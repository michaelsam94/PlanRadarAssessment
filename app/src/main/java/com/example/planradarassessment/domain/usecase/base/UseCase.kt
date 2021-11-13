package com.example.planradarassessment.domain.usecase.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class UseCase {
    protected var lastDisposable: Disposable? = null
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun disposeLast(){
        lastDisposable.let {
            if(it?.isDisposed?.not() == true) it.dispose()
        }
    }
}