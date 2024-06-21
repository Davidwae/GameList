package com.waekaizo.gamelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
