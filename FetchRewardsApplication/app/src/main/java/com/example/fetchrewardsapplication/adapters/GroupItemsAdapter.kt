package com.example.fetchrewardsapplication.adapters

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.*
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.fetchrewardsapplication.R
import com.example.fetchrewardsapplication.databinding.GroupItemsLayoutBinding
import com.example.fetchrewardsapplication.model.GroupItemsData

/***
 *  created by Harsha Sri Praneeth Konduru
 *  Adapter for [rcGroupView] present in [fragment_main_activity] layout.
 *  Enhancement: Bindable Adapter can be used to show different views in single recycler view.
 */
private const val TAG_NAME = "GroupItemsAdapter"
class GroupItemsAdapter: RecyclerView.Adapter<GroupItemsAdapter.ViewHolder>() {

    /***
     * compares the previous data present in the recyclerview with the latest API Response data.
     */
    private val diffUtilCallBack = object : DiffUtil.ItemCallback<GroupItemsData>() {
        override fun areItemsTheSame(oldItem: GroupItemsData, newItem: GroupItemsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GroupItemsData, newItem: GroupItemsData): Boolean {
            return oldItem == newItem
        }
    }

    private val differList = AsyncListDiffer(this, diffUtilCallBack)

    /***
     * Containts the items requried to display in [rcGroupView] present in [fragment_main_activty] layout.
     */
    var list : List<GroupItemsData>
        get() = differList.currentList
        set(value) {
            differList.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GroupItemsLayoutBinding.inflate( LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        /***
         * Assigning the data to respective views.
         */
        holder.binding.apply {
            tvListId.text = tvListId.resources.getString(R.string.text_tvlisid,currentItem.listId)
        }

        /***
         * Adapter for [rcItemView] present in [item_layout].
         */
        val itemAdapter = ItemAdapter(currentItem.itemData)
        holder.binding.rcItemView.apply{
            setHasFixedSize(true)
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(holder.binding.root.context)
        }

        /***
         * Transitions for animating the [rcItemView].
         */
        holder.binding.groupAdapterLinearLayout.apply{
            TransitionManager.beginDelayedTransition(this,AutoTransition())
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            setOnClickListener{
                val itemsView = holder.binding.rcItemView
                val tvHint = holder.binding.tvHint
                itemsView.visibility = if(itemsView.isVisible) View.GONE else View.VISIBLE

                tvHint.hint = if(itemsView.isVisible)
                                resources.getString(R.string.hint_recyclerview_visible)
                else  resources.getString(R.string.hint_recyclerview_notvisible)
            }
        }
        holder.binding.rcItemView.addItemDecoration(DividerItemDecoration(holder.binding.root.context,LinearLayout.VERTICAL))
    }

    /**
     * Gives the size of the list from the [list].
     */
    override fun getItemCount(): Int {
        Log.d(TAG_NAME ,list.size.toString())
        return list.size
    }


    inner class ViewHolder( val binding: GroupItemsLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

}