package com.project.estudo.presentation.detailsFragment

sealed class DetailsAction {
    data class BackToReturn(val result: String) : DetailsAction()
    data class GetCalculation(val calculation: String) : DetailsAction()
}