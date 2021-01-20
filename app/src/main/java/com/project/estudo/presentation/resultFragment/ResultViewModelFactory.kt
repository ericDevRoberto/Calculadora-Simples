package com.project.estudo.presentation.resultFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.estudo.dataBase.dao.OldResultDao

class ResultViewModelFactory(
    private val finalResult: String,
    private val dataSource: OldResultDao,
) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(
                finalResult = finalResult,
                dataBase = dataSource,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}