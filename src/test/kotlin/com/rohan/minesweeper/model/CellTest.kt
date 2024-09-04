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
}