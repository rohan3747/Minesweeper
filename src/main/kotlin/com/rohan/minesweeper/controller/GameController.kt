package com.rohan.minesweeper.controller

import com.rohan.minesweeper.utils.GameInputHandler
import com.rohan.minesweeper.utils.GameUtils.parseCol
import com.rohan.minesweeper.utils.GameUtils.parseRow
import com.rohan.minesweeper.utils.Messages
import com.rohan.minesweeper.core.Minesweeper
import com.rohan.minesweeper.model.GameResult

/**
 * Class to control the flow of the game.
 *
 * @param game The Minesweeper game instance that this controller will manage.
 * @param inputHandler The input handler to manage user interactions.
 */
class GameController(private val game: Minesweeper, private val inputHandler: GameInputHandler) {

    /**
     * Main method to play the game.
     */
    fun play() {
        while (!game.isGameOver() && !game.isGameWon()) {
            game.printGrid()
            val input = inputHandler.readString(Messages.get("select_squire"))
            val row = input.parseRow(game.getSize()) ?: continue
            val col = input.parseCol(game.getSize()) ?: continue

            when (val result = processMove(row, col)) {
                GameResult.GameOver -> {
                    println(Messages.get("game_over"))
                    return
                }
                GameResult.GameWon -> {
                    println(Messages.get("game_won"))
                    return
                }
                is GameResult.MoveMade -> {
                    println("This square contains ${result.adjacentMines} adjacent mines.")
                }

                GameResult.InvalidInput -> TODO()
            }
        }
    }

    /**
     * Processes the player's move based on the given row and column.
     *
     * @param row The row index of the cell to reveal.
     * @param col The column index of the cell to reveal.
     * @return The result of the move, which can be GameOver, GameWon, MoveMade, or InvalidInput.
     */
    fun processMove(row: Int, col: Int): GameResult {
        return when {
            !game.isInBounds(row, col) -> {
                println(Messages.get("input_invalid_number"))
                GameResult.InvalidInput
            }
            game.revealCell(row, col) -> GameResult.GameOver
            game.isGameWon() -> {
                game.printGrid(isGridUpdate = true)
                GameResult.GameWon
            }
            else -> GameResult.MoveMade(game.adjacentMines(row, col))
        }
    }
}
