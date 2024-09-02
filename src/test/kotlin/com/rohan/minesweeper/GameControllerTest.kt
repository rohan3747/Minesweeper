package com.rohan.minesweeper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class GameControllerTest {

    private val game = mock(Minesweeper::class.java)
    private val inputHandler = mock(GameInputHandler::class.java)
    private val controller = GameController(game, inputHandler)

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

        // Act
        val result = controller.processMove(row, col)

        // Assert
        assertEquals(GameController.GameResult.MoveMade(adjacentMines), result)
    }

    @Test
    fun `test processMove with invalid input`() {
        // Arrange
        val row = 1
        val col = 2
        `when`(game.isInBounds(row, col)).thenReturn(false)

        // Act
        val result = controller.processMove(row, col)

        // Assert
        assertEquals(GameController.GameResult.InvalidInput, result)
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

        // Act
        val result = controller.processMove(row, col)

        // Assert
        assertEquals(GameController.GameResult.GameWon, result)
    }

    @Test
    fun `test processMove with mine hit`() {
        // Arrange
        val row = 1
        val col = 2
        `when`(game.isInBounds(row, col)).thenReturn(true)
        `when`(game.adjacentMines(row, col)).thenReturn(3)
        `when`(game.revealCell(row, col)).thenReturn(true)

        // Act
        val result = controller.processMove(row, col)

        // Assert
        assertEquals(GameController.GameResult.GameOver, result)
    }
}