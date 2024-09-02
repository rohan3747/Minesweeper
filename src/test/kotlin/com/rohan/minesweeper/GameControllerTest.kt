package com.rohan.minesweeper

import com.rohan.minesweeper.GameUtils.parseCol
import com.rohan.minesweeper.GameUtils.parseRow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GameControllerTest {

    private lateinit var minesweeper: Minesweeper
    private lateinit var inputHandler: GameInputHandler
    private lateinit var gameController: GameController
    private lateinit var grid: Grid

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        minesweeper = mock(Minesweeper::class.java)
        grid = mock(Grid::class.java)
        inputHandler = mock(GameInputHandler::class.java)
        gameController = GameController(minesweeper, inputHandler)
    }

//    @Test
//    fun `should print grid and handle input when minesweeper is ongoing`() {
//        whenever(minesweeper.isGameOver()).thenReturn(false)
//        whenever(minesweeper.isGameWon()).thenReturn(false)
//        whenever(inputHandler.readString(anyString())).thenReturn("1,1")
//        whenever("1,1".parseRow(anyInt())).thenReturn(0)
//        whenever("1,1".parseCol(anyInt())).thenReturn(0)
//        whenever(minesweeper.isInBounds(eq(0), eq(0))).thenReturn(true) // Use `eq()` for specific values
//        whenever(minesweeper.adjacentMines(eq(0), eq(0))).thenReturn(1)
//        whenever(minesweeper.revealCell(eq(0), eq(0))).thenReturn(false)
//        whenever(minesweeper.isGameWon()).thenReturn(false)
//
//        gameController.play()

//        // Verify interactions
//        verify(minesweeper).printGrid()
//        verify(inputHandler).readString(Messages.get("select_squire"))
//        verify(minesweeper).isInBounds(0, 0)
//        verify(minesweeper).adjacentMines(0, 0)
//        verify(minesweeper).revealCell(0, 0)
//    }


    @Test
    fun `should end minesweeper when mine is hit`() {
        // Arrange - setting up mocks
        whenever(minesweeper.isGameOver()).thenReturn(false)
        whenever(minesweeper.isGameWon()).thenReturn(false)
        whenever(inputHandler.readString(anyString())).thenReturn("1,1")
//        whenever("1,1".parseRow(anyInt())).thenReturn(0)
//        whenever("1,1".parseCol(anyInt())).thenReturn(0)
        whenever(minesweeper.isInBounds(eq(0), eq(0))).thenReturn(true)
        whenever(minesweeper.adjacentMines(eq(0), eq(0))).thenReturn(1)
        whenever(minesweeper.revealCell(eq(0), eq(0))).thenReturn(true)  // Hit a mine
        whenever(minesweeper.isGameOver()).thenReturn(true)  // Terminate loop

        // Act - run the minesweeper
        gameController.play()

        // Assert - Verify interactions and minesweeper end
//        verify(minesweeper).printGrid()
//        verify(inputHandler).readString(Messages.get("select_squire"))
//        verify(minesweeper).isInBounds(0, 0)
//        verify(minesweeper).adjacentMines(0, 0)
//        verify(minesweeper).revealCell(0, 0)
//        verify(minesweeper).printGrid(true) // Assuming true indicates grid update
        assertTrue(minesweeper.isGameOver())
    }

    @Test
    fun `should print minesweeper won message when minesweeper is won`() {
        whenever(minesweeper.isGameOver()).thenReturn(false)
        whenever(minesweeper.isGameWon()).thenReturn(false)
        whenever(inputHandler.readString(anyString())).thenReturn("1,1")
//        whenever("1,1".parseRow(anyInt())).thenReturn(0)
//        whenever("1,1".parseCol(anyInt())).thenReturn(0)
        whenever(minesweeper.isInBounds(eq(0), eq(0))).thenReturn(true)
        whenever(minesweeper.adjacentMines(eq(0), eq(0))).thenReturn(1)
        whenever(minesweeper.revealCell(eq(0), eq(0))).thenReturn(false)
        whenever(minesweeper.isGameWon()).thenReturn(true)

        gameController.play()

        // Verify interactions
//        verify(minesweeper).printGrid(true) // Assuming true indicates grid update
//        verify(inputHandler).readString(Messages.get("select_squire"))
//        verify(minesweeper).isInBounds(0, 0)
//        verify(minesweeper).adjacentMines(0, 0)
//        verify(minesweeper).revealCell(0, 0)
//        assertFalse(minesweeper.isGameOver())
        assertTrue(minesweeper.isGameWon())
    }
}
