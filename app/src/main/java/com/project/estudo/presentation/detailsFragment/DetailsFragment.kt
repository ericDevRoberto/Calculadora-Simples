package com.project.estudo.presentation.detailsFragment

import android.os.Bundle
import android.telecom.Call
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.project.estudo.R
import com.project.estudo.dataBase.DataBase
import com.project.estudo.presentation.resultFragment.ResultFragmentArgs
import com.project.estudo.presentation.resultFragment.ResultFragmentDirections
import com.project.estudo.presentation.resultFragment.ResultViewModelFactory
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var viewModel: DetailsViewModel

    private lateinit var viewModelFactory: DetailsViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application

        val dataSource = DataBase.getInstance(application).oldResultDao

        viewModelFactory =
            DetailsViewModelFactory(
                finalResult = DetailsFragmentArgs.fromBundle(requireArguments()).finalResult,
                dataSource = dataSource,
                resultId = DetailsFragmentArgs.fromBundle(requireArguments()).resultId
            )

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        bnt_calculation_back.setOnClickListener {

            viewModel.backToResult()
        }

        viewModel.mutableLiveData.observe(viewLifecycleOwner, { action ->

            when (action) {
                is DetailsAction.BackToReturn -> toGoResultFragment(action.result)
                is DetailsAction.GetCalculation -> putCalculation(action.calculation)
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            NavHostFragment.findNavController(requireParentFragment()).navigate(
                DetailsFragmentDirections.actionNavigationDetailsToNavigationHome()
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavHostFragment.findNavController(this)
            .navigate(DetailsFragmentDirections.actionNavigationDetailsToNavigationHome())
        return super.onOptionsItemSelected(item)
    }

    fun putCalculation(calculation: String) {

        textview_calculation_details.text = calculation
    }

    fun toGoResultFragment(finalResult: String) {

        NavHostFragment.findNavController(this).navigate(
            DetailsFragmentDirections.actionNavigationDetailsToNavigationResult(finalResult)
        )
    }
}