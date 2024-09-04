package com.rohan.minesweeper.utils

import java.util.logging.Logger

/**
 * Object providing utility functions for the game.
 */
object GameUtils {
    private val logger = Logger.getLogger(GameUtils::class.java.name)

    /**
     * Parses the row character from the input string.
     * @param size The size of the grid.
     * @return The row index or null if invalid.
     */
    fun String.parseRow(size: Int): Int? {
        return if (isNotEmpty() && this[0].uppercaseChar().isLetter()) {
            this[0].uppercaseChar().minus('A').takeIf { it in 0 until size }
        } else {
            null
        }
    }

    /**
     * Parses the column number from the input string.
     * @param size The size of the grid.
     * @return The column index or null if invalid.
     */
    fun String.parseCol(size: Int): Int? {
        return if (length > 1 && substring(1).toIntOrNull()?.minus(1) in 0 until size) {
            substring(1).toIntOrNull()?.minus(1)
        } else {
            null
        }
    }
}