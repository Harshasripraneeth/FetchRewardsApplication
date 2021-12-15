package com.example.fetchrewardsapplication.model

/**
 * Created by Harsha Sri Praneeth.
 * @property [id] of the item
 * @property [listId] of the item
 * @param [name] of the item
 */

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class APIData(val id: Int,val listId: Int, val name: String ):Parcelable
