package com.rohan.minesweeper

object MinesweeperConfig {
    val minGridSize: Int = System.getProperty("minGridSize")?.toIntOrNull() ?: 2
    val maxGridSize: Int = System.getProperty("maxGridSize")?.toIntOrNull() ?: 10
    val minMineCount: Int = System.getProperty("minMineCount")?.toIntOrNull() ?: 1
    val maxMinePercentage: Double = System.getProperty("maxMinePercentage")?.toDoubleOrNull() ?: 0.35
}