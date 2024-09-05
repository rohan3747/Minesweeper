package com.rohan.minesweeper.controller

import com.rohan.minesweeper.core.Minesweeper
import com.rohan.minesweeper.utils.GameInputHandler
import com.rohan.minesweeper.utils.Messages
import io.mockk.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GameRunnerTest {

    private lateinit var inputHandler: GameInputHandler
    private lateinit var messages: Messages
    private lateinit var gameRunner: GameRunner

    @BeforeTest
    fun setup() {
        inputHandler = mockk()
        messages = mockk()
        gameRunner = GameRunner(inputHandler, messages)
    }

    @Test
    fun `test displayWelcomeMessage should print welcome message`() {
        every { messages.get("welcome_message") } returns "Welcome to Minesweeper!"

        // Capturing output
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        gameRunner.displayWelcomeMessage()

        assertEquals("Welcome to Minesweeper!\n", outputStream.toString())
    }

    @Test
    fun `test createGame should return a Minesweeper instance`() {
        val size = 5
        val numMines = 10

        val game = gameRunner.createGame(size, numMines)

        assertEquals(Minesweeper::class, game::class)
        assertEquals(numMines, game.numMines)
    }


    @Test
    fun `test getNumberOfMines should return correct mine count`() {
        val size = 5
        every { messages.get("mine_count_prompt") } returns "Enter number of mines:"
        every { inputHandler.readMineCount(any(), size) } returns 10

        val numMines = gameRunner.getNumberOfMines(size)

        assertEquals(8, numMines)
    }


    @Test
    fun `test promptForReplay should return true if user continues`() {
        every { inputHandler.promptForContinuation() } returns true

        val result = gameRunner.promptForReplay()

        assertEquals(true, result)
    }

    @Test
    fun `test playGame should call GameController play`() {
        val game = mockk<Minesweeper>()

        // Mocking constructor for GameController
        mockkConstructor(GameController::class)
        every { anyConstructed<GameController>().play() } just Runs

        gameRunner.playGame(game)

        verify { anyConstructed<GameController>().play() }
    }
}
