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
        val size = game.getSize()
        var isUpdate: Boolean

        while (!game.isGameOver() && !game.isGameWon()) {
            game.printGrid()
            val input = inputHandler.readString( Messages.get("select_squire"))
            val row = input.parseRow(size) ?: continue
            val col = input.parseCol(size) ?: continue
            isUpdate = true

            if (!game.isInBounds(row, col)) {
                println(Messages.get("input_invalid_number"))
                continue
            }

            val adjacentMines = game.adjacentMines(row, col)

            val hitMine = game.revealCell(row, col)
            if (hitMine) {
                println(Messages.get("game_over"))
                return
            } else if (game.isGameWon()) {
                println("This square contains $adjacentMines adjacent mines.")
                game.printGrid(isGridUpdate = isUpdate)
                println(Messages.get("game_won"))
                return
            } else{
                println("This square contains $adjacentMines adjacent mines.")
            }
        }
    }
}
