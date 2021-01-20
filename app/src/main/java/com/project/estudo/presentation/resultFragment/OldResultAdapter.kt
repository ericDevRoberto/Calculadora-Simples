package com.project.estudo.presentation.resultFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.estudo.R
import com.project.estudo.domain.model.OldResultTable

class OldResultAdapter :
    ListAdapter<OldResultTable, OldResultAdapter.ViewHolder>(OldResultDiffCallback()) {

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object{
            fun from(parent: ViewGroup) :ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_old_result, parent, false)
                return ViewHolder(view)
            }
        }

        private val textItem: TextView = itemView.findViewById(R.id.item_oldResult)

        fun bind(item : OldResultTable){
            textItem.text = item.oldResultValue
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder.from(parent)
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