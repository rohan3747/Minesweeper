package com.rohan.minesweeper.config

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for the [Constants] object to ensure that all constants
 * have the correct values.
 */
class ConstantsTest {

    @Test
    fun `verify MIN_GRID_SIZE is correct`() {
        assertEquals(2, Constants.MIN_GRID_SIZE, "MIN_GRID_SIZE should be 2")
    }

    @Test
    fun `verify MAX_GRID_SIZE is correct`() {
        assertEquals(10, Constants.MAX_GRID_SIZE, "MAX_GRID_SIZE should be 10")
    }

    @Test
    fun `verify MIN_MINE_COUNT is correct`() {
        assertEquals(1, Constants.MIN_MINE_COUNT, "MIN_MINE_COUNT should be 1")
    }

    @Test
    fun `verify MAX_MINE_PERCENTAGE is correct`() {
        assertEquals(0.35, Constants.MAX_MINE_PERCENTAGE, "MAX_MINE_PERCENTAGE should be 0.35")
    }
}
