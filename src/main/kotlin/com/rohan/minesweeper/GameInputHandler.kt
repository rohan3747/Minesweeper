package com.rohan.minesweeper

import com.rohan.minesweeper.MinesweeperConfig.minGridSize
import com.rohan.minesweeper.MinesweeperConfig.minMineCount

class GameInputHandler {

    fun readGridSize(prompt: String, minValue: Int = minGridSize): Int {
        while (true) {
            println(prompt)
            val input = readlnOrNull()

            try {
                val value = input?.toInt()
                if (value != null && value >= minValue) {
                    return value
                } else {
                    println("Input is invalid. Please try again and enter a value greater than or equal to $minValue.")
                }
            } catch (e: NumberFormatException) {
                println("Input is invalid. Please enter a valid number.")
            }
        }
    }

    fun readMineCount(prompt: String, gridSize: Int): Int {
        val maxMines = (gridSize * gridSize * 0.35).toInt()
        while (true) {
            println(prompt)
            val userInput = readlnOrNull()
            try {
                val value = userInput?.toInt()
                if (value != null && value in minMineCount..maxMines) {
                    return value
                } else {
                    println("Input is invalid. Please enter a value $minMineCount to $maxMines.")
                }
            } catch (e: NumberFormatException) {
                println(Messages.get("input_invalid_number"))
            }
        }
    }

    fun readString(prompt: String): String {
        print(prompt)
        return readlnOrNull().orEmpty()
    }
}
