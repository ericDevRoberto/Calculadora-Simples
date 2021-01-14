package com.project.estudo.presentation.homeFragment

sealed class HomeAction {
    data class Success(val result: String) : HomeAction()
    object Fail : HomeAction()
}