package com.kpstv.spotifyapi.data.models

data class Browse(
    val categories: Categories
) {
    data class Categories(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
            val href: String,
            val icons: List<Icon>,
            val id: String,
            val name: String
        ) {
            data class Icon(
                val height: Int?,
                val url: String,
                val width: Int?
            )
        }
    }
}