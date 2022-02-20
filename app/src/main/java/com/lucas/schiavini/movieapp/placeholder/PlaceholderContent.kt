package com.lucas.schiavini.movieapp.placeholder

import com.lucas.schiavini.movieapp.model.Movie
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<Movie> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Movie> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                Movie(
                    title = "aaaaaa",
                    id = "i",
                    releaseDate= "2022/02/02",
                    duration ="3 hours",
                    description = "bbbbb",
                    score = "2/5",
                    imageUrl = "https://www.pinclipart.com/picdir/big/407-4078267_shopping-electricity-supplier-transaction-online-shopping-drawing-clipart.png"
                )
            )
        }
    }

    private fun addItem(item: Movie) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

}