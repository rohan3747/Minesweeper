package com.rohan.minesweeper

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

class MinesweeperConfigTest {

    @BeforeEach
    fun setUp() {
        // Clear any pre-existing system properties to ensure a clean test environment.
        System.clearProperty("minGridSize")
        System.clearProperty("maxGridSize")
        System.clearProperty("minMineCount")
        System.clearProperty("maxMinePercentage")
    }

    @AfterEach
    fun tearDown() {
        // Clear system properties after each test to avoid side effects.
        System.clearProperty("minGridSize")
        System.clearProperty("maxGridSize")
        System.clearProperty("minMineCount")
        System.clearProperty("maxMinePercentage")
    }

    @Test
    fun `should use default values when system properties are not set`() {
        // Act
        val minGridSize = MinesweeperConfig.minGridSize
        val maxGridSize = MinesweeperConfig.maxGridSize
        val minMineCount = MinesweeperConfig.minMineCount
        val maxMinePercentage = MinesweeperConfig.maxMinePercentage

        // Assert
        assertEquals(2, minGridSize)
        assertEquals(10, maxGridSize)
        assertEquals(1, minMineCount)
        assertEquals(0.35, maxMinePercentage)
    }

    @Test
    fun `should use system property values when they are set`() {
        // Arrange
        System.setProperty("minGridSize", "2")
        System.setProperty("maxGridSize", "10")
        System.setProperty("minMineCount", "1")
        System.setProperty("maxMinePercentage", "0.35")

        // Act
        val minGridSize = MinesweeperConfig.minGridSize
        val maxGridSize = MinesweeperConfig.maxGridSize
        val minMineCount = MinesweeperConfig.minMineCount
        val maxMinePercentage = MinesweeperConfig.maxMinePercentage

        // Assert
        assertEquals(2, minGridSize)
        assertEquals(10, maxGridSize)
        assertEquals(1, minMineCount)
        assertEquals(0.35, maxMinePercentage)
    }

    @Test
    fun `should fallback to default values when system properties are not valid integers or doubles`() {
        // Arrange
        System.setProperty("minGridSize", "invalid")
        System.setProperty("maxGridSize", "invalid")
        System.setProperty("minMineCount", "invalid")
        System.setProperty("maxMinePercentage", "invalid")

        // Act
        val minGridSize = MinesweeperConfig.minGridSize
        val maxGridSize = MinesweeperConfig.maxGridSize
        val minMineCount = MinesweeperConfig.minMineCount
        val maxMinePercentage = MinesweeperConfig.maxMinePercentage

        // Assert
        assertEquals(2, minGridSize)
        assertEquals(10, maxGridSize)
        assertEquals(1, minMineCount)
        assertEquals(0.35, maxMinePercentage)
    }
}