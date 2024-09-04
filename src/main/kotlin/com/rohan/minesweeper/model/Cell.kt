package com.rohan.minesweeper.model

// Data class representing a single cell on the Minesweeper grid
data class Cell(
    var isMine: Boolean = false,            // Indicates if the cell contains a mine
    var isRevealed: Boolean = false,        // Indicates if the cell has been revealed
    var adjacentMines: Int = 0              // Number of adjacent mines around the cell
)