package com.project.estudo.presentation.homeFragment

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.project.estudo.R
import com.project.estudo.dataBase.DataBase
import com.project.estudo.utils.Operators
import com.project.estudo.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application

        val dataSource = DataBase.getInstance(application).oldResultDao

        homeViewModelFactory = HomeViewModelFactory(dataSource)

        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)


        homeViewModel.mutableLiveData.observe(viewLifecycleOwner, { action ->

            when (action) {
                is HomeAction.Success -> Success(action.result)
                is HomeAction.Fail -> Fail()
            }
        })

        buttons()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }


    }

    private fun buttons(){
        calculadora_bnt_sum.setOnClickListener {
            toGoResult(Operators.SUM)
        }

        calculadora_bnt_minus.setOnClickListener {
            toGoResult(Operators.MINUS)
        }

        calculadora_bnt_multiply.setOnClickListener {
            toGoResult(Operators.MULTIPLY)
        }

        calculadora_bnt_divide.setOnClickListener {
            toGoResult(Operators.DIVIDE)
        }
    }

    private fun toGoResult(operator: Operators) {
        hideKeyboard()
        homeViewModel.calculator(
            calculadora_edittext_valor_one.text.toString(),
            calculadora_edittext_valor_two.text.toString(),
            operator
        )
    }

    private fun Success(result: String) {
        val action =
            HomeFragmentDirections.actionNavigationHomeToNavigationResult(result)
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun Fail() {
        view?.let {
            Snackbar.make(it, "Por favor coloque os valores", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}