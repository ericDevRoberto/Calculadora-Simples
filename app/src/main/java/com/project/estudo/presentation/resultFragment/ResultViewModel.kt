package com.project.estudo.presentation.resultFragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.estudo.dataBase.dao.OldResultDao
import kotlinx.coroutines.launch

class ResultViewModel(
    finalResult: String,
    val dataBase: OldResultDao,
) : ViewModel() {

    private var _mutablelivedata: MutableLiveData<ResultAction> = MutableLiveData()
    val mutablelivedata
        get() = _mutablelivedata

    init {

        getLastResult(finalResult)
    }

    private fun getLastResult(finalResult: String) {
        viewModelScope.launch {
            val lastResult = dataBase.getLastResult()

            if (lastResult.oldResultId != 1L){
                val oldResult = dataBase.getNextLastResult()

                ResultsSuccess(finalResult, oldResult.oldResultValue)
            }
            else
                ResultsOldResultNull(finalResult)
        }
    }

    private fun ResultsSuccess(finalResult: String, oldResult: String) {
        _mutablelivedata.value = ResultAction.Success(finalResult, oldResult)
    }

    private fun ResultsOldResultNull(finalResult: String) {
        _mutablelivedata.value = ResultAction.OldResultNull(finalResult, "Empty")
    }

    fun backToHome() {
        _mutablelivedata.value = ResultAction.BackHome
    }
}