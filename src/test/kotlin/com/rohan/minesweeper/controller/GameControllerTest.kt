package com.rohan.minesweeper.controller

import com.rohan.minesweeper.core.Minesweeper
import com.rohan.minesweeper.model.GameResult
import com.rohan.minesweeper.utils.GameInputHandler
import com.rohan.minesweeper.utils.Messages
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GameControllerTest {

    private val game = mock(Minesweeper::class.java)
    private val inputHandler = mock(GameInputHandler::class.java)
    private val controller = GameController(game, inputHandler)
    private lateinit var messageProvider: Messages
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        messageProvider = mock(Messages::class.java)
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `test processMove with valid input`() {
        // Arrange
        val row = 1
        val col = 2
        val adjacentMines = 3
        `when`(game.isInBounds(row, col)).thenReturn(true)
        `when`(game.adjacentMines(row, col)).thenReturn(adjacentMines)
        `when`(game.revealCell(row, col)).thenReturn(false)
        `when`(game.isGameWon()).thenReturn(false)

        val result = controller.processMove(row, col)
        assertEquals(GameResult.MoveMade(adjacentMines), result)
    }


    @Test
    fun `test processMove with invalid input`() {
        // Arrange
        val row = 1
        val col = 2
        `when`(game.isInBounds(row, col)).thenReturn(false)

        val result = controller.processMove(row, col)

        assertEquals(GameResult.InvalidInput, result)
    }

    @Test
    fun `test processMove with game won`() {
        // Arrange
        val row = 1
        val col = 2
        `when`(game.isInBounds(row, col)).thenReturn(true)
        `when`(game.adjacentMines(row, col)).thenReturn(3)
        `when`(game.revealCell(row, col)).thenReturn(false)
        `when`(game.isGameWon()).thenReturn(true)

        val result = controller.processMove(row, col)

        assertEquals(GameResult.GameWon, result)
    }

    @Test
    fun `test processMove with mine hit`() {
        // Arrange
        val row = 1
        val col = 2
        `when`(game.isInBounds(row, col)).thenReturn(true)
        `when`(game.adjacentMines(row, col)).thenReturn(3)
        `when`(game.revealCell(row, col)).thenReturn(true)

        val result = controller.processMove(row, col)

        assertEquals(GameResult.GameOver, result)
    }

    @Test
    fun `play should handle game over state`() {
        `when`(game.isGameOver()).thenReturn(true)
        `when`(game.isGameWon()).thenReturn(false)

        controller.play()
    }

    @Test
    fun `play should handle game won state`() {
        `when`(game.isGameWon()).thenReturn(true)
        `when`(game.isGameOver()).thenReturn(false)

        controller.play()
    }
}