import com.rohan.minesweeper.Cell
import com.rohan.minesweeper.Grid
import com.rohan.minesweeper.Minesweeper
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

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        grid = mock(Grid::class.java)
        minesweeper = Minesweeper(size = 5, numMines = 5)
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

    /*
    @Test
    fun `test revealCell hits a mine`() {
        // Assume a mine is at (1, 1)
        val hitMine = minesweeper.revealCell(1, 1)
        assertTrue(hitMine)
        assertTrue(minesweeper.isGameOver())
    }

     */

//    @Test
//    fun `test revealCell does not hit a mine`() {
//        // Assume no mine is at (0, 0)
//        val hitMine = minesweeper.revealCell(0, 0)
//        assertFalse(hitMine)
//        assertFalse(minesweeper.isGameOver())
//    }



//    @Test
//    fun `test adjacentMines returns correct count`() {
//        // Assume (1, 1) has 3 adjacent mines
//        val adjacentMines = minesweeper.adjacentMines(1, 1)
//        assertEquals(3, adjacentMines)
//    }

    /*
    @Test
    fun `test isGameWon returns true when all non-mine cells are revealed`() {
        // Assume the grid is fully revealed without mines
        // This is a placeholder; you need to set up the grid state for testing
        // e.g., reveal all non-mine cells in the setup or with specific methods
        assertTrue(minesweeper.isGameWon())
    }

    @Test
    fun `test isGameOver returns true after hitting a mine`() {
        // Assume a mine is hit
        minesweeper.revealCell(1, 1)
        assertTrue(minesweeper.isGameOver())
    }

     */

    @Test
    fun `test printGrid outputs correct grid format`() {
        // Redirect output stream to capture print statements
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        minesweeper.printGrid()
        val output = outputStream.toString()

        // Check if the output contains expected strings
//        assertTrue(output.contains("Here is your updated minefield:") || output.contains(Messages.get("mine_field")))
        assertTrue(output.contains("_ ") || output.contains("* "))
    }
}

