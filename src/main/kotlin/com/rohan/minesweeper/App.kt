package com.rohan.minesweeper

import com.rohan.minesweeper.controller.MinesweeperGameRunner
import com.rohan.minesweeper.utils.GameInputHandler

/**
 * Entry point for the Minesweeper game.
 */
fun main() {
    val inputHandler = GameInputHandler()
    val gameRunner = MinesweeperGameRunner(inputHandler)
    gameRunner.run()
}