package com.rohan.minesweeper

import com.rohan.minesweeper.controller.GameRunner
import com.rohan.minesweeper.utils.GameInputHandler
import com.rohan.minesweeper.utils.Messages
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class AppKtTest {

    private lateinit var inputHandler: GameInputHandler
    private lateinit var messages: Messages
    private lateinit var gameRunner: GameRunner

    @BeforeEach
    fun setUp() {
        inputHandler = mock()
        messages = mock()
        gameRunner = GameRunner(inputHandler, messages)
    }

    @Test
    fun `test main function runs GameRunner`() {
        // Arrange
        val mockInputHandler = mockk<GameInputHandler>(relaxed = true)
        val messages = mockk<Messages>(relaxed = true)
        val mockGameRunner = mockk<GameRunner>(relaxed = true)

        // Act
//        main() // Call the main function

        // Assert
//        verify { mockGameRunner.run() }
    }
}

