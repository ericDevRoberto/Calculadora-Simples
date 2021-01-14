package com.project.estudo.presentation.resultFragment

sealed class ResultAction {
    data class Success(val result: String, val oldResult: String) : ResultAction()
    data class OldResultNull(val result: String, val oldResult: String) : ResultAction()
    object BackHome : ResultAction()
}