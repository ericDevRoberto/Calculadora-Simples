package com.project.estudo.draft

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DraftViewModel<E> : ViewModel() {

    val mutableLiveData: MutableLiveData<E> = MutableLiveData()
}