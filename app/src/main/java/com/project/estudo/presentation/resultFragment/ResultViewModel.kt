package com.project.estudo.presentation.resultFragment

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

    val allResults = dataBase.getAllResults()

    init {
        ResultsSuccess(finalResult)
    }


    private fun ResultsSuccess(finalResult: String) {
        _mutablelivedata.value = ResultAction.Success(finalResult)
    }

    fun goToDetails(){
        _mutablelivedata.value = ResultAction.GoToDetails
    }

    fun backToHome() {
        _mutablelivedata.value = ResultAction.BackHome
    }

    fun clearResultData(){
        viewModelScope.launch {
            dataBase.clearResults()
        }
    }
}