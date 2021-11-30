package com.example.lista.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var info1: String,
    var info2: String
    )
