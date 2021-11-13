package com.example.planradarassessment.domain.usecase.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T> : UseCase() {

    abstract fun buildSingle(): Single<T>

    fun execute(
        onSuccess: (data: T?) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        disposeLast()
        lastDisposable =
            buildSingle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError)
        lastDisposable.let {
            compositeDisposable.add(it!!)
        }
    }

}