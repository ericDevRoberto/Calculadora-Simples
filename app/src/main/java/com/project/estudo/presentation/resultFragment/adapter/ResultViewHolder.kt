package com.project.estudo.presentation.resultFragment.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.estudo.R
import com.project.estudo.domain.model.OldResultTable

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textItem: TextView = itemView.findViewById(R.id.item_oldResult)
    private val itemClick: TextView = itemView.findViewById(R.id.item_oldResult)

    fun bind(item: OldResultTable, clickRecycleViewListener: ResultRecycleViewListener) {

        textItem.text = item.oldResultValue
        itemClick.setOnClickListener {

            clickRecycleViewListener.onClick(item)
        }
    }
}

class ResultRecycleViewListener(val clickListener: (resultId: Long) -> Unit) {

    fun onClick(result: OldResultTable) = clickListener(result.oldResultId)
}