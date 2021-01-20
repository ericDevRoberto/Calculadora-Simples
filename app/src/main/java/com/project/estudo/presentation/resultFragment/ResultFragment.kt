package com.project.estudo.presentation.resultFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        //ViewModelFactory and ViewModel Declarations
        viewModelFactory =
            ResultViewModelFactory(
                finalResult = ResultFragmentArgs.fromBundle(requireArguments()).finalResult,
                dataSource = dataSource
            )

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        //Bottoms
        bnt_back.setOnClickListener {
            viewModel.backToHome()
        }

        bnt_clear.setOnClickListener {
            viewModel.clearResultData()
        }

        //RecycleView
        val adapter = OldResultAdapter(OldResultListener { resultId ->
            Toast.makeText(context, "$resultId", Toast.LENGTH_SHORT).show()
            viewModel.goToDetails()
        })

        list_old_result.adapter = adapter

        viewModel.allResults.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        //Actions
        viewModel.mutablelivedata.observe(viewLifecycleOwner, { action ->

            when (action) {
                is ResultAction.BackHome -> backHome()
                is ResultAction.Success -> success(action.result)
                is ResultAction.GoToDetails -> goToDetailsFragment()
            }
        })
    }

    private fun success(result: String) {
        text_dashboard.text = result
    }

    private fun goToDetailsFragment(){
        NavHostFragment.findNavController(this)
            .navigate(ResultFragmentDirections.actionNavigationResultToNavigationDetails())
    }

    private fun backHome() {
        NavHostFragment.findNavController(this)
            .navigate(ResultFragmentDirections.actionNavigationResultToNavigationHome())
    }
}