package com.project.estudo.presentation.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.estudo.dataBase.dao.OldResultDao
import com.project.estudo.domain.model.OldResultTable
import com.project.estudo.utils.Operators
import kotlinx.coroutines.launch

class HomeViewModel(val dataBase: OldResultDao) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<HomeAction> = MutableLiveData()
    val mutableLiveData
        get() = _mutableLiveData

    private fun putResultInTable(dbDataResult: String, dbDataCalculation: String) {
        viewModelScope.launch {
            val db = OldResultTable()
            db.oldResultValue = dbDataResult
            db.oldResultCalculation = dbDataCalculation
                dataBase.insert(db)
        }
    }

    fun calculator(val1: String, val2: String, symbol: Operators) {
        if (val1.isEmpty() || val2.isEmpty())
            _mutableLiveData.value = HomeAction.Fail
        else {
            val result = when (symbol) {
                Operators.SUM -> (val1.toDouble() + val2.toDouble()).toString()
                Operators.MINUS -> (val1.toDouble() - val2.toDouble()).toString()
                Operators.MULTIPLY -> (val1.toDouble() * val2.toDouble()).toString()
                Operators.DIVIDE -> (val1.toDouble() / val2.toDouble()).toString()
            }

            val calculation = when (symbol) {
                Operators.SUM -> "${val1} + ${val2} = ${result}"
                Operators.MINUS -> "${val1} - ${val2} = ${result}"
                Operators.MULTIPLY -> "${val1} x ${val2} = ${result}"
                Operators.DIVIDE -> "${val1} / ${val2} = ${result}"
            }
            putResultInTable(result, calculation)

            _mutableLiveData.value = HomeAction.Success(result = result)
        }
    }
}