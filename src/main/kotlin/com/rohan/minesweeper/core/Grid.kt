package com.rohan.minesweeper.core

import com.rohan.minesweeper.model.Cell
import kotlin.random.Random

/**
 * Class representing the grid of the Minesweeper game.
 * Handles the grid operations, including cell management and mine placement.
 */
class Grid(private val size: Int, private val numMines: Int) {
    private val grid = Array(size) { Array(size) { Cell() } }

    init {
        placeMines()
        calculateAdjacentMines()
    }

    /**
     * Places mines randomly on the grid.
     */
    private fun placeMines() {
        var minesPlaced = 0
        while (minesPlaced < numMines) {
            val row = Random.nextInt(size)
            val col = Random.nextInt(size)
            if (!grid[row][col].isMine) {
                grid[row][col].isMine = true
                minesPlaced++
            }
        }
    }

    /**
     * Calculates the number of adjacent mines for each cell.
     */
    private fun calculateAdjacentMines() {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (!grid[row][col].isMine) {
                    grid[row][col].adjacentMines = countAdjacentMines(row, col)
                }
            }
        }
    }

    /**
     * Counts the number of mines adjacent to the specified cell.
     */
    private fun countAdjacentMines(row: Int, col: Int): Int {
        var count = 0
        for (i in -1..1) {
            for (j in -1..1) {
                if (isInBounds(row + i, col + j) && grid[row + i][col + j].isMine) {
                    count++
                }
            }
        }
        return count
    }

    /**
     * Returns the cell at the specified row and column.
     */
    fun getCell(row: Int, col: Int): Cell {
        return grid[row][col]
    }

    /**
     * Checks if the given coordinates are within the grid boundaries.
     */
    fun isInBounds(row: Int, col: Int): Boolean {
        return row in 0 until size && col in 0 until size
    }

    /**
     * Reveals the cell at the specified row and column.
     * @return true if a mine is revealed, false otherwise.
     */
    fun revealCell(row: Int, col: Int): Boolean {
        if (!isInBounds(row, col) || grid[row][col].isRevealed) return false

        grid[row][col].isRevealed = true

        if (grid[row][col].isMine) {
            return true
        }

        if (grid[row][col].adjacentMines == 0) {
            revealAdjacentCells(row, col)
        }

        return false
    }

    /**
     * Reveals the cells adjacent to the specified cell if they have no adjacent mines.
     */
    private fun revealAdjacentCells(row: Int, col: Int) {
        for (i in -1..1) {
            for (j in -1..1) {
                val newRow = row + i
                val newCol = col + j
                if (isInBounds(newRow, newCol) && !grid[newRow][newCol].isRevealed) {
                    revealCell(newRow, newCol)
                }
            }
        }
    }

    /**
     * Returns the grid array.
     */
    fun getGrid(): Array<Array<Cell>> {
        return grid
    }
}