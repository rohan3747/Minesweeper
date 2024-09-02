package com.rohan.minesweeper

import com.rohan.minesweeper.GameUtils.parseCol
import com.rohan.minesweeper.GameUtils.parseRow

/**
 * Class to control the flow of the game.
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

    fun processMove(row: Int, col: Int): GameResult {
        if (!game.isInBounds(row, col)) {
            println(Messages.get("input_invalid_number"))
            return GameResult.InvalidInput
        }

        val adjacentMines = game.adjacentMines(row, col)
        val hitMine = game.revealCell(row, col)

        if (hitMine) {
            return GameResult.GameOver
        } else if (game.isGameWon()) {
            game.printGrid(isGridUpdate = true)
            return GameResult.GameWon
        } else {
            return GameResult.MoveMade(adjacentMines)
        }
    }

    sealed class GameResult {
        data object GameOver : GameResult()
        data object GameWon : GameResult()
        data object InvalidInput : GameResult()
        data class MoveMade(val adjacentMines: Int) : GameResult()
    }
}
