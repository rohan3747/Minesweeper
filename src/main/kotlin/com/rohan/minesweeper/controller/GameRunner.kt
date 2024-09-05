package com.rohan.minesweeper.controller

import com.rohan.minesweeper.utils.GameInputHandler
import com.rohan.minesweeper.utils.Messages
import com.rohan.minesweeper.core.Minesweeper
import kotlin.system.exitProcess

/**
 * Implementation of the GameRunner interface.
 *
 * @property inputHandler Handles user input and interactions.
 * @property messages Provides localized messages for the game.
 */
class GameRunner(
    private val inputHandler: GameInputHandler,
    private val messages: Messages = Messages
) {

    fun run() {
        while (true) {
            displayWelcomeMessage()

            val size = inputHandler.readGridSize(messages.get("grid_size_prompt"))
            val numMines = getNumberOfMines(size)

            val game = createGame(size, numMines)
            playGame(game)

            if (!promptForReplay()) {
                exitProgram()
            }
        }
    }

    /**
     * Displays the welcome message to the user.
     */
    fun displayWelcomeMessage() {
        println(messages.get("welcome_message"))
    }

    /**
     * Creates a new Minesweeper game with the specified size and number of mines.
     *
     * @param size The size of the grid.
     * @param numMines The number of mines.
     * @return The created Minesweeper game instance.
     */
    fun createGame(size: Int, numMines: Int): Minesweeper {
        return Minesweeper(size, numMines)
    }

    /**
     * Determines the number of mines, ensuring it does not exceed the maximum allowed.
     *
     * @param size The size of the grid.
     * @return The number of mines.
     */
    fun getNumberOfMines(size: Int): Int {
        return inputHandler.readMineCount(messages.get("mine_count_prompt"), size)
            .coerceAtMost((size * size * 0.35).toInt())
    }

    /**
     * Starts and manages the game using the provided Minesweeper instance.
     *
     * @param game The Minesweeper game instance to be played.
     */
    fun playGame(game: Minesweeper) {
        val controller = GameController(game, inputHandler)
        controller.play()
    }

    /**
     * Prompts the user to decide whether to play again or exit.
     *
     * @return True if the user wants to play again, false otherwise.
     */
    fun promptForReplay(): Boolean {
        return inputHandler.promptForContinuation()
    }

    /**
     * Exits the program with a message.
     */
    private fun exitProgram() {
        println(messages.get("exit_message"))
        exitProcess(0)
    }
}