package com.rohan.minesweeper

import com.rohan.minesweeper.controller.GameRunner
import com.rohan.minesweeper.utils.GameInputHandler

/**
 * Entry point for the Minesweeper game application.
 *
 * This function initializes the game input handler and game runner, then starts the game.
 *
 * - Creates an instance of [GameInputHandler] to handle user inputs.
 * - Creates an instance of [GameRunner] with the input handler to manage game execution.
 * - Calls [GameRunner.run] to start and manage the game loop.
 */
fun main() {
    // Create an instance of GameInputHandler to handle user inputs.
    val inputHandler = GameInputHandler()

    // Create an instance of GameRunner, passing the input handler to it.
    // The GameRunner will use this handler to get user inputs and manage the game.
    val gameRunner = GameRunner(inputHandler)
    // Start the game by calling the run method on GameRunner.
    // This will handle the game loop and interact with the user through the input handler.
    gameRunner.run()
}