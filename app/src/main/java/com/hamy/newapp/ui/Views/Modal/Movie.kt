package com.hamy.newapp.ui.Views.Modal

data class Movie(
    val title: String,
    val year: Int,
    val genre: String,
    val imageUrl: String
) {
    val displayTitle: String
        get() = "$title ($year)"
}

