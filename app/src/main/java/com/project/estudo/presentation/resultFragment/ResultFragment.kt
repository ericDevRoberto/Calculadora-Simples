package com.project.estudo.presentation.resultFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.project.estudo.R
import com.project.estudo.dataBase.DataBase
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var viewModel: ResultViewModel

    private lateinit var viewModelFactory: ResultViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application

        val dataSource = DataBase.getInstance(application).oldResultDao

        viewModelFactory =
            ResultViewModelFactory(
                finalResult = ResultFragmentArgs.fromBundle(requireArguments()).finalResult,
                dataSource = dataSource
            )

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        bnt_back.setOnClickListener {
            viewModel.backToHome()
        }

        viewModel.mutablelivedata.observe(viewLifecycleOwner, { action ->

            when (action) {
                is ResultAction.BackHome -> backHome()
                is ResultAction.Success -> success(action.result, action.oldResult)
                is ResultAction.OldResultNull -> oldResultNull(action.result, action.oldResult)
            }
        })
    }

    private fun success(result: String, oldResult: String) {
        text_dashboard.text = result
        text_old_result.text = oldResult
    }

    private fun oldResultNull(result: String, oldResult: String) {
        text_dashboard.text = result
        text_old_result.text = oldResult
    }

    private fun backHome() {
        NavHostFragment.findNavController(this)
            .navigate(ResultFragmentDirections.actionNavigationResultToNavigationHome())
    }
}