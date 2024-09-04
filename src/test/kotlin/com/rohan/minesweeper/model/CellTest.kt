package com.rohan.minesweeper.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `test default cell initialization`() {
        val cell = Cell()
        assertFalse(cell.isMine)
        assertFalse(cell.isRevealed)
        assertEquals(0, cell.adjacentMines)
    }

    @Test
    fun `test setting and getting cell properties`() {
        val cell = Cell()
        cell.isMine = true
        cell.isRevealed = true
        cell.adjacentMines = 3

        assertTrue(cell.isMine)
        assertTrue(cell.isRevealed)
        assertEquals(3, cell.adjacentMines)
    }

    @Test
    fun `test MoveMade initialization`() {
        // Arrange
        val expectedAdjacentMines = 3

        // Act
        val moveMade = GameResult.MoveMade(expectedAdjacentMines)

        // Assert
        assertEquals(expectedAdjacentMines, moveMade.adjacentMines, "The adjacentMines property should be correctly initialized.")
    }

    @Test
    fun `test MoveMade equals and hashCode`() {
        // Arrange
        val moveMade1 = GameResult.MoveMade(3)
        val moveMade2 = GameResult.MoveMade(3)
        val moveMade3 = GameResult.MoveMade(5)

        // Act & Assert
        assertEquals(moveMade1, moveMade2, "MoveMade instances with the same adjacentMines should be equal.")
        assertEquals(moveMade1.hashCode(), moveMade2.hashCode(), "Hash codes should be equal for MoveMade instances with the same adjacentMines.")
        assertNotEquals(moveMade1, moveMade3, "MoveMade instances with different adjacentMines should not be equal.")
        assertNotEquals(moveMade1.hashCode(), moveMade3.hashCode(), "Hash codes should not be equal for MoveMade instances with different adjacentMines.")
    }

    @Test
    fun `test MoveMade toString`() {
        // Arrange
        val moveMade = GameResult.MoveMade(3)
        val expectedToString = "MoveMade(adjacentMines=3)"

        // Act
        val actualToString = moveMade.toString()

        // Assert
        assertEquals(expectedToString, actualToString, "The toString method should return the expected string representation.")
    }
}