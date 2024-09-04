package com.rohan.minesweeper.core

import com.rohan.minesweeper.utils.Messages
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
        minesweeper = Minesweeper(size = 5, numMines = 10)
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
        // Arrange
        val minesweeper = Minesweeper(size = 10, numMines = 5)

        // Act
        val isGameOver = minesweeper.isGameOver()

        // Assert
        assertFalse(isGameOver, "Expected isGameOver to return false initially")
    }
}

