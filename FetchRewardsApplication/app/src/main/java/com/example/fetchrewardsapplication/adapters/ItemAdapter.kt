package com.example.fetchrewardsapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.R
import com.example.fetchrewardsapplication.databinding.ItemLayoutBinding
import com.example.fetchrewardsapplication.model.RecyclerViewData


/***
 *  created by Harsha Sri Praneeth Konduru
 *  Adapter for [rcItemView] present in [group_items_layout] layout.
 *  Enhancement: Bindable adapter can be used to show different views in single recycler view.
 */

private const val TAG_NAME = "ChildAdapter"
class ItemAdapter(val list: List<RecyclerViewData.ItemData>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate( LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        /***
         * Assigning the data to respective views.
         */

        holder.binding.apply {
            tvId.text = tvId.resources.getString(R.string.text_tvid,currentItem.id)
            tvName.text = tvName.resources.getString(R.string.text_tvname,currentItem.name)
        }
    }

    /**
     * Gives the size of the list from the [list].
     */
    override fun getItemCount(): Int {
        Log.d(TAG_NAME ,list.size.toString())
        return list.size
    }


    inner class ViewHolder( val binding: ItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

}

