package com.example.geotask.ui.startPoint

import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geotask.R
import com.example.geotask.databinding.ViewItemSearchResultBinding

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    var addresses: List<Address> = emptyList()
    var onItemClicked: ((Address) -> Unit)? = null

    override fun getItemCount(): Int {
        return addresses.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_search_result, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(addresses[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ViewItemSearchResultBinding.bind(itemView)
        fun bind(value: Address) {
            binding.textView.text = StringBuilder()
                .append(value.countryCode)
                .append(" ")
                .append(value.countryName)
            binding.textView.setOnClickListener {
                onItemClicked?.invoke(value)
            }
        }
    }
}

