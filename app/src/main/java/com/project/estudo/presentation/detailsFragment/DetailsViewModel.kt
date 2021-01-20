package com.project.estudo.presentation.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.estudo.dataBase.dao.OldResultDao
import com.project.estudo.draft.DraftViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(
    finalResult: String,
    val dataBase: OldResultDao,
    resultId: Long
) : DraftViewModel<DetailsAction>() {

    var result = String()

    init {

        result = finalResult
        getCalcule(resultId)
    }

    fun backToResult() {

        mutableLiveData.value = DetailsAction.BackToReturn(result)
    }

    fun getCalcule(calculation: Long) {

        viewModelScope.launch {

            val specificResult = dataBase.getAResult(calculation)

            mutableLiveData.value = DetailsAction.GetCalculation(specificResult.oldResultCalculation)
        }
    }
}