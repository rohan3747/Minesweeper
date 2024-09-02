package com.rohan.minesweeper

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class GameInputHandlerTest {

    private val gameInputHandler = GameInputHandler()

    private val originalSystemIn = System.`in`
    private val originalSystemOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        System.setIn(originalSystemIn)
        System.setOut(originalSystemOut)
    }

    private fun setInput(input: String) {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
    }

    @Test
    fun `test readGridSize with valid input`() {
        setInput("5\n")
        val result = gameInputHandler.readGridSize("Enter grid size:", 3)
        assertEquals(5, result)
    }

    @Test
    fun `test readGridSize with invalid input`() {
        setInput("invalid\n4\n2\n")
        val result = gameInputHandler.readGridSize("Enter grid size:", 3)
        assertEquals(4, result)
    }

    @Test
    fun `test readMineCount with valid input`() {
        setInput("10\n")
        val result = gameInputHandler.readMineCount("Enter mine count:", 10)
        assertEquals(10, result)
    }

    @Test
    fun `test readMineCount with invalid input`() {
        setInput("invalid\n100\n5\n")
        val result = gameInputHandler.readMineCount("Enter mine count:", 10)
        assertEquals(5, result)
    }

    @Test
    fun `test readString`() {
        setInput("test string\n")
        val result = gameInputHandler.readString("Enter string:")
        assertEquals("test string", result)
    }
}

