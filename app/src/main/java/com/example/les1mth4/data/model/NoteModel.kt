package com.example.les1mth4.data.model

import android.net.Uri


data class NoteModel (
    val image: Uri,
    val title: String,
    val description: String,
    val date: String
)
