package com.example.itsjustsample.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by hclee on 2019-08-09.
 */

open class BaseViewModel : ViewModel() {
    protected val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        mCompositeDisposable.clear()

        super.onCleared()
    }
}