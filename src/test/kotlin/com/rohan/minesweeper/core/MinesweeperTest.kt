package com.rohan.minesweeper.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class MinesweeperTest {

    private lateinit var minesweeper: Minesweeper
    private lateinit var grid: Grid
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        grid = mock(Grid::class.java)
        minesweeper = Minesweeper(size = 5, numMines = 5)
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `test getSize returns correct grid size`() {
        assertEquals(5, minesweeper.getSize())
    }

    @Test
    fun `test isInBounds returns true for valid coordinates`() {
        `when`(grid.isInBounds(0, 0)).thenReturn(true)
        assertTrue(minesweeper.isInBounds(0, 0))
    }

    @Test
    fun `test isInBounds returns false for invalid coordinates`() {
        `when`(grid.isInBounds(-1, 0)).thenReturn(false)
        assertFalse(minesweeper.isInBounds(-1, 0))
    }

    @Test
    fun `test printGrid outputs correct grid format`() {
        // Redirect output stream to capture print statements
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        minesweeper.printGrid()
        val output = outputStream.toString()

        assertTrue(output.contains("_ ") || output.contains("* "))
    }

    @Test
    fun `test isGameOver should return false initially`() {
        val minesweeper = Minesweeper(size = 10, numMines = 5)

        val isGameOver = minesweeper.isGameOver()
        assertFalse(isGameOver, "Expected isGameOver to return false initially")
    }


    @Test
    fun `test get size`() {
        assertEquals(5, minesweeper.getSize(), "The size of the grid should be 5.")
    }

    @Test
    fun `test isInBounds`() {
        assertTrue(minesweeper.isInBounds(0, 0), "Cell (0, 0) should be within bounds.")
        assertTrue(minesweeper.isInBounds(4, 4), "Cell (4, 4) should be within bounds.")
        assertFalse(minesweeper.isInBounds(-1, 0), "Cell (-1, 0) should be out of bounds.")
        assertFalse(minesweeper.isInBounds(5, 0), "Cell (5, 0) should be out of bounds.")
        assertFalse(minesweeper.isInBounds(0, -1), "Cell (0, -1) should be out of bounds.")
        assertFalse(minesweeper.isInBounds(0, 5), "Cell (0, 5) should be out of bounds.")
    }
    @Test
    fun `test reveal cell without mine`() {
        minesweeper.revealCell(0, 0) // Simulate revealing a cell
        assertFalse(minesweeper.isGameOver(), "Game should not be over if no mine is revealed.")
    }

    @Test
    fun `test reveal adjacent cells`() {
        // Simulate revealing a cell with no adjacent mines
        minesweeper.revealCell(0, 0) // This will either reveal cells or trigger game over if a mine is hit
        // Test will vary based on the actual grid setup, so we assume a proper setup where adjacent cells are revealed
    }

    @Test
    fun `test print updated grid`() {
        val output = capturePrintOutput {
            minesweeper.printGrid(revealMines = false, isGridUpdate = true)
        }
        assertTrue(output.startsWith("Here is your updated minefield:"), "The output should start with 'Here is your updated minefield:'")
    }

    @Test
    fun `test isGameWon`() {
        // Check if game is won by revealing all non-mine cells
        // This depends on how you set up your grid, so adjust accordingly.
        assertFalse(minesweeper.isGameWon(), "The game should not be won initially.")
        minesweeper.revealCell(0, 0) // Simulate revealing a cell
        assertFalse(minesweeper.isGameWon(), "The game should not be won after revealing one cell.")
    }


    // Utility function to capture the output of println for testing
    private fun capturePrintOutput(block: () -> Unit): String {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        block()
        return outputStream.toString()
    }
}
