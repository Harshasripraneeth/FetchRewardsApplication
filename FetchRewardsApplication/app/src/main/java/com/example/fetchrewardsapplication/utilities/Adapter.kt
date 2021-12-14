package com.example.fetchrewardsapplication.utilities

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.databinding.RecyclerViewLayoutBinding
import com.example.fetchrewardsapplication.model.APIData

class Adapter: RecyclerView.Adapter<Adapter.viewHolder>() {


    //check whether the data items are changed or not.
    private val diffUtilCallBack = object : DiffUtil.ItemCallback<APIData>() {
        override fun areItemsTheSame(oldItem: APIData, newItem: APIData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: APIData, newItem: APIData): Boolean {
            return oldItem == newItem
        }
    }


    private val differList = AsyncListDiffer(this, diffUtilCallBack)

    var list : List<APIData>
        get() = differList.currentList
        set(value) {
            differList.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(RecyclerViewLayoutBinding.inflate( LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val curItem = list[position]

        holder.binding.apply {
            tvId.text = "ID: ${curItem.id}"
            tvListId.text = "ListId: ${curItem.listId}"
            tvName.text = "Name: ${curItem.name}"

        }
    }

    override fun getItemCount(): Int {
        Log.d("rcview" ,list.size.toString())
        return list.size
    }


    inner class viewHolder( val binding: RecyclerViewLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }



}