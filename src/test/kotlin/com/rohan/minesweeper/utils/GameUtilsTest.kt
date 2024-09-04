package com.rohan.minesweeper.utils

import com.rohan.minesweeper.utils.GameUtils.parseCol
import com.rohan.minesweeper.utils.GameUtils.parseRow
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GameUtilsTest {

    @Test
    fun `test valid row parsing`() {
        assertEquals(0, "A1".parseRow(10))  // 'A' -> 0
        assertEquals(1, "B1".parseRow(10))  // 'B' -> 1
        assertEquals(25, "Z1".parseRow(26)) // 'Z' -> 25
    }

    @Test
    fun `test invalid row parsing`() {
        assertNull("A1".parseRow(0))       // 'A' not valid in grid size 0
        assertNull("Z1".parseRow(25))      // 'Z' -> 25 but grid size is 25, so it's invalid
        assertNull("B1".parseRow(1))       // 'B' -> 1 but grid size is 1, so it's invalid
    }

    @Test
    fun `test empty row input`() {
        assertNull("".parseRow(10))         // Empty input should return null
    }

    @Test
    fun testValidRowInput() {
        assertEquals(0, "A1".parseRow(5))
        assertEquals(1, "B1".parseRow(5))
        assertEquals(null, "F1".parseRow(5)) // Out of bounds
    }

    @Test
    fun testInvalidRowInput() {
        assertNull("".parseRow(5)) // Empty string
        assertNull("1A".parseRow(5)) // Invalid format
    }

    @Test
    fun testValidColInput() {
        assertEquals(0, "A1".parseCol(5))
        assertEquals(1, "A2".parseCol(5))
        assertEquals(null, "A6".parseCol(5)) // Out of bounds
    }

    @Test
    fun testInvalidColInput() {
        assertNull("A".parseCol(5)) // Missing column number
        assertNull("A-1".parseCol(5)) // Invalid column number
    }


    @Test
    fun `test valid column parsing`() {
        assertEquals(0, "A1".parseCol(10))  // '1' -> 0 (0-based index)
        assertEquals(1, "A2".parseCol(10))  // '2' -> 1
        assertEquals(9, "A10".parseCol(10)) // '10' -> 9
    }

    @Test
    fun `test invalid column parsing`() {
        assertNull("A0".parseCol(10))       // '0' -> -1 which is invalid
        assertNull("A11".parseCol(10))      // '11' -> 10 which is invalid
        assertNull("A".parseCol(10))        // Non-numeric part should return null
    }

    @Test
    fun `test empty column input`() {
        assertNull("A".parseCol(10))         // No column number should return null
    }

    @Test
    fun `test invalid column input`() {
        assertNull("A1A".parseCol(10))       // Invalid column format
        assertNull("A-1".parseCol(10))       // Negative column number
    }

}
