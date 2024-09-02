package com.rohan.minesweeper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GridTest {

    private lateinit var grid: Grid

    @BeforeEach
    fun setUp() {
        grid = Grid(size = 5, numMines = 5) // Create a 5x5 grid with 5 mines
    }

    @Test
    fun `test mine placement`() {
        val cellsWithMines = grid.getGrid().flatten().count { it.isMine }
        assertEquals(5, cellsWithMines, "The grid should contain exactly 5 mines.")
    }

    /*
    @Test
    fun `test adjacent mines calculation`() {
        // Manually setting up a small 3x3 grid for testing adjacent mine calculation
        val testGrid = Grid(size = 3, numMines = 1)
        testGrid.getGrid()[1][1].isMine = true
        testGrid.getGrid()[1][1].adjacentMines = 0 // Setting this as mine manually

        // Recalculate adjacent mines after manually placing one mine
        testGrid.getGrid().forEach { row ->
            row.forEach { cell ->
                cell.adjacentMines = 0 // Reset adjacent mines for fresh calculation
            }
        }
        testGrid.getGrid().forEachIndexed { row, rowCells ->
            rowCells.forEachIndexed { col, _ ->
                if (!testGrid.getGrid()[row][col].isMine) {
                    testGrid.getGrid()[row][col].adjacentMines = testGrid.getCell(row, col).adjacentMines
                }
            }
        }

        assertEquals(1, testGrid.getCell(0, 0).adjacentMines, "Cell (0, 0) should have 1 adjacent mine.")
        assertEquals(1, testGrid.getCell(2, 2).adjacentMines, "Cell (2, 2) should have 1 adjacent mine.")
    }

     */

    @Test
    fun `test reveal cell with mine`() {
        grid.getGrid()[0][0].isMine = true // Manually place a mine at (0, 0)
        val result = grid.revealCell(0, 0)
        assertTrue(result, "Revealing a mine should return true.")
        assertTrue(grid.getCell(0, 0).isRevealed, "Cell (0, 0) should be revealed.")
    }

    @Test
    fun `test reveal cell without mine`() {
        grid.getGrid()[0][0].isMine = false // Ensure no mine at (0, 0)
        val result = grid.revealCell(0, 0)
        assertFalse(result, "Revealing a non-mine cell should return false.")
        assertTrue(grid.getCell(0, 0).isRevealed, "Cell (0, 0) should be revealed.")
    }

    @Test
    fun `test reveal adjacent cells`() {
        grid.getGrid()[0][0].isMine = false
        grid.getGrid()[0][0].adjacentMines = 0
        grid.getGrid()[1][0].isMine = false
        grid.getGrid()[1][0].adjacentMines = 0
        grid.getGrid()[0][1].isMine = false
        grid.getGrid()[0][1].adjacentMines = 0
        grid.getGrid()[1][1].isMine = false
        grid.getGrid()[1][1].adjacentMines = 0

        grid.revealCell(0, 0)
        assertTrue(grid.getCell(1, 0).isRevealed, "Cell (1, 0) should be revealed after revealing (0, 0).")
        assertTrue(grid.getCell(0, 1).isRevealed, "Cell (0, 1) should be revealed after revealing (0, 0).")
    }

    @Test
    fun `test grid boundaries`() {
        assertTrue(grid.isInBounds(0, 0), "Cell (0, 0) should be within bounds.")
        assertFalse(grid.isInBounds(-1, 0), "Cell (-1, 0) should be out of bounds.")
        assertFalse(grid.isInBounds(5, 0), "Cell (5, 0) should be out of bounds.")
        assertTrue(grid.isInBounds(4, 4), "Cell (4, 4) should be within bounds.")
    }
}
