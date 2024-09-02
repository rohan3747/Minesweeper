package com.rohan.minesweeper

import java.util.*

object Messages {
    private val bundle: ResourceBundle = ResourceBundle.getBundle("messages_en")

    /**
     * Retrieves the localized message for the given key.
     * @param key The key for the desired message.
     * @return The localized message corresponding to the key.
     */
    fun get(key: String): String {
        return bundle.getString(key)
    }
}