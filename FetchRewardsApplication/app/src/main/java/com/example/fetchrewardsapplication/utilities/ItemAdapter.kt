package com.example.fetchrewardsapplication.utilities

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.databinding.ItemLayoutBinding
import com.example.fetchrewardsapplication.model.RecyclerViewData


/***
 *  created by Harsha Sri Praneeth Konduru
 *  Adapter for [rcItemView] present in [group_items_layout] layout.
 *  Enhancement: Bindable adapter can be used to show different views in single recycler view.
 */
class ItemAdapter(val list: List<RecyclerViewData.ItemData>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate( LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = list[position]

        /***
         * Assigning the data to respective views.
         */

        holder.binding.apply {
            val idText = "ID: ${curItem.id}"
            val nameText = "Name: ${curItem.name}"
            tvId.text = idText
            tvName.text = nameText
        }
    }

    /**
     * Gives the size of the list from the [list].
     */
    override fun getItemCount(): Int {
        Log.d("rcChildView" ,list.size.toString())
        return list.size
    }


    inner class ViewHolder( val binding: ItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

}

