package com.example.fetchrewardsapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class APIData(val id: Int,val listId: Int, val name: String ):Parcelable
