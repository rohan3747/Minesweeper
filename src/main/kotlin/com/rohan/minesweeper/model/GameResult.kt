package com.rohan.minesweeper.model

/**
 * Sealed class representing the possible outcomes of a player's move.
 */
sealed class GameResult {
    /**
     * Represents a game-over state where the player has hit a mine.
     */
    data object GameOver : GameResult()
    /**
     * Represents a game-won state where the player has successfully cleared all non-mine cells.
     */
    data object GameWon : GameResult()
    /**
     * Represents an invalid input, where the player's move is outside the grid bounds.
     */
    data object InvalidInput : GameResult()
    /**
     * Represents a valid move, indicating the number of adjacent mines.
     *
     * @param adjacentMines The number of mines adjacent to the revealed cell.
     */
    data class MoveMade(val adjacentMines: Int) : GameResult()
}