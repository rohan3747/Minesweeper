package com.rohan.minesweeper.core

import com.rohan.minesweeper.utils.Messages
import java.util.logging.Logger

/**
 * Class representing the Minesweeper game.
 * Manages the game state and logic.
 */
class Minesweeper(private val size: Int, val numMines: Int) {
    private val grid = Grid(size, numMines)
    private var gameOver = false
    private val logger = Logger.getLogger(Minesweeper::class.java.name)

    /**
     * Gets the size of the grid.
     */
    fun getSize(): Int {
        return size
    }

    /**
     * Checks if the given coordinates are within the grid boundaries.
     */
    fun isInBounds(row: Int, col: Int): Boolean {
        return grid.isInBounds(row, col)
    }

    /**
     * Reveals the cell at the specified row and column.
     * @return True if a mine was hit, otherwise false.
     */
    fun revealCell(row: Int, col: Int): Boolean {
        if (gameOver) return false
        val hitMine = grid.revealCell(row, col)
        if (hitMine) {
            gameOver = true
//            logger.info("Mine hit at ($row, $col). Game over.")
        }
        return hitMine
    }

    fun adjacentMines(row: Int, col: Int): Int {
        val cell = grid.getCell(row, col)
        return cell.adjacentMines
    }

    /**
     * Checks if the player has won the game.
     */
    fun isGameWon(): Boolean {
        return grid.getGrid().flatten().all { it.isRevealed || it.isMine }
    }

    /**
     * Prints the current state of the grid.
     * @param revealMines If true, mines will be revealed.
     */
    fun printGrid(revealMines: Boolean = false, isGridUpdate: Boolean = false) {

        if (isGridUpdate){
            println("Here is your updated minefield:")
        }else{
            println(Messages.get("mine_field"))
        }
        println("  ${(1..size).joinToString(" ")}")
        for (row in 0 until size) {
            print("${('A' + row)} ")
            for (col in 0 until size) {
                val cell = grid.getCell(row, col)
                when {
                    cell.isRevealed && cell.isMine -> print("* ") // Display * if the mine is revealed
                    cell.isRevealed -> print("${cell.adjacentMines} ")
                    revealMines && cell.isMine -> print("* ")
                    else -> print("_ ")
                }
            }
            println()
        }
    }

    /**
     * Checks if the game is over.
     */
    fun isGameOver(): Boolean {
        return gameOver
    }
}