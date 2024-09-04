package com.rohan.minesweeper.utils

import com.rohan.minesweeper.config.Constants.MAX_GRID_SIZE
import com.rohan.minesweeper.config.Constants.MIN_GRID_SIZE
import com.rohan.minesweeper.config.Constants.MIN_MINE_COUNT
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Handles user input for the Minesweeper game.
 * Provides methods for reading grid size, mine count, string input, and handling continuation prompts.
 */
class GameInputHandler(private val inputStream: InputStream = System.`in`) {

    /**
     * Reads the grid size from the user.
     * Ensures the input is a valid integer greater than or equal to the minimum value.
     *
     * @param prompt The message to display to the user.
     * @param minValue The minimum acceptable grid size. Defaults to minGridSize.
     * @return The valid grid size entered by the user.
     */
    fun readGridSize(prompt: String, minValue: Int = MIN_GRID_SIZE, maxValue: Int = MAX_GRID_SIZE): Int {
        while (true) {
            println(prompt)
            val input = readlnOrNull() // Read user input

            try {
                val value = input?.toInt() // Attempt to convert input to integer
                if (value != null && value in minValue..maxValue) {
                    return value // Return valid input
                } else {
                    println("Input is invalid. Please try again and enter a value between $minValue and $maxValue.")
                }
            } catch (e: NumberFormatException) {
                println("Input is invalid. Please enter a valid number.") // Handle non-integer input
            }
        }
    }

    /**
     * Reads the number of mines from the user.
     * Ensures the input is a valid integer within the acceptable range.
     *
     * @param prompt The message to display to the user.
     * @param gridSize The size of the grid, used to calculate the maximum number of mines.
     * @return The valid number of mines entered by the user.
     */
    fun readMineCount(prompt: String, gridSize: Int): Int {
        val maxMines = (gridSize * gridSize * 0.35).toInt() // Calculate maximum mines allowed
        while (true) {
            println(prompt)
            val userInput = readlnOrNull() // Read user input
            try {
                val value = userInput?.toInt() // Attempt to convert input to integer
                if (value != null && value in MIN_MINE_COUNT..maxMines) {
                    return value // Return valid input
                } else {
                    println("Input is invalid. Please enter a value $MIN_MINE_COUNT to $maxMines.")
                }
            } catch (e: NumberFormatException) {
                println(Messages.get("input_invalid_number")) // Handle non-integer input
            }
        }
    }

    /**
     * Reads a string input from the user.
     *
     * @param prompt The message to display to the user.
     * @return The string entered by the user.
     */
    fun readString(prompt: String): String {
        print(prompt)
        return readlnOrNull().orEmpty() // Read input and return an empty string if input is null
    }

    /**
     * Prompts the user to continue or exit.
     *
     * @return True if any key is pressed, false if Esc key is pressed.
     */
    fun promptForContinuation(): Boolean {
        println("Press any key to continue, or Esc to exit...")
        val input = readKey() // Read a single character from user input
        return input != '\u001B' // Return true if any key is pressed except Esc
    }

    /**
     * Reads a single character from user input.
     *
     * @return The character read from the input. Returns a space if there's an error.
     */
    fun readKey(): Char {
        return try {
            val input = InputStreamReader(inputStream).read() // Read single character from input
            input.toChar() // Convert to character
        } catch (e: Exception) {
            println("Error reading input: ${e.message}") // Handle errors
            ' ' // Return a space if there's an error
        }
    }
}
