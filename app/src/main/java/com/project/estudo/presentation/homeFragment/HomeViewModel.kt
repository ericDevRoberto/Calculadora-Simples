package com.project.estudo.presentation.homeFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.estudo.dataBase.dao.OldResultDao
import com.project.estudo.domain.model.OldResultTable
import com.project.estudo.utils.Operators
import kotlinx.coroutines.launch

class HomeViewModel(val dataBase: OldResultDao, application: Application) :
    AndroidViewModel(application) {

    private val _mutableLiveData: MutableLiveData<HomeAction> = MutableLiveData()
    val mutableLiveData
        get() = _mutableLiveData

    private fun putResultInTable(dbData: String) {
        viewModelScope.launch {
            val db = OldResultTable()
            db.oldResultValue = dbData
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
            putResultInTable(result)

            _mutableLiveData.value = HomeAction.Success(result = result)
        }
    }
}