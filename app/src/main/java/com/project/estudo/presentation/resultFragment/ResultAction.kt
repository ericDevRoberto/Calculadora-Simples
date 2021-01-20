package com.project.estudo.presentation.resultFragment

sealed class ResultAction {
    data class Success(val result: String) : ResultAction()
    object BackHome : ResultAction()
    data class GoToDetails(val finalResult: String, val resultId: Long) : ResultAction()
}