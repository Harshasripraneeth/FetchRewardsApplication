package com.example.fetchrewardsapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/***
 * created by Harsha Sri Praneeth
 * The [RecyclerViewData] is used to create the list of items group by listId.
 * [ItemData] format is used to display the items for [rcItemView].
 */
@Parcelize
data class RecyclerViewData(val map: MutableMap<Int, MutableList<ItemData>>): Parcelable{

    @Parcelize
    data class ItemData(val id: Int, val name: String):Parcelable
}
