package com.project.estudo.presentation.resultFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.estudo.R
import com.project.estudo.domain.model.OldResultTable
import com.project.estudo.presentation.resultFragment.adapter.ResultRecycleViewListener
import com.project.estudo.presentation.resultFragment.adapter.ResultViewHolder

class ResultAdapter(private val clickRecycleViewListener: ResultRecycleViewListener) :
    ListAdapter<OldResultTable, ResultViewHolder>(OldResultDiffCallback()) {

    override fun onBindViewHolder(holderResult: ResultViewHolder, position: Int) {

        val item = getItem(position)
        holderResult.bind(item, clickRecycleViewListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_old_result, parent, false)
        return ResultViewHolder(view)
    }
}

class OldResultDiffCallback : DiffUtil.ItemCallback<OldResultTable>() {

    override fun areItemsTheSame(oldItem: OldResultTable, newItem: OldResultTable): Boolean {
        return oldItem.oldResultId == newItem.oldResultId
    }

    override fun areContentsTheSame(oldItem: OldResultTable, newItem: OldResultTable): Boolean {
        return oldItem.equals(newItem)
    }
}

