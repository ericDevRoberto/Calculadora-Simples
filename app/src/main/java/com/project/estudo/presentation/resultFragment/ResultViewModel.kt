package com.project.estudo.presentation.resultFragment

import androidx.lifecycle.viewModelScope
import com.project.estudo.dataBase.dao.OldResultDao
import com.project.estudo.draft.DraftViewModel
import kotlinx.coroutines.launch

class ResultViewModel(
    finalResult: String,
    val dataBase: OldResultDao,
) : DraftViewModel<ResultAction>() {

    val allResults = dataBase.getAllResults()

    var result = String()

    init {
        result = finalResult
        ResultsSuccess(finalResult)
    }

    private fun ResultsSuccess(finalResult: String) {

        mutableLiveData.value = ResultAction.Success(finalResult)
    }

    fun goToDetails(resultId: Long) {

        mutableLiveData.value = ResultAction.GoToDetails(result, resultId)
    }

    fun backToHome() {

        mutableLiveData.value = ResultAction.BackHome
    }

    fun clearResultData() {

        viewModelScope.launch {
            dataBase.clearResults()
        }
    }
}