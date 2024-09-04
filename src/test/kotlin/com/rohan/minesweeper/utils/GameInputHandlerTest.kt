package com.rohan.minesweeper.utils

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
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
    fun `test readGridSize with valid input within range`() {
        // Arrange: Set input to a value within the valid range
        setInput("5\n")

        // Act: Call the method with minValue and maxValue parameters
        val result = gameInputHandler.readGridSize("Enter grid size:", minValue = 3, maxValue = 10)

        // Assert: Check that the result matches the input value
        assertEquals(5, result)
    }

    @Test
    fun `test readGridSize with input outside the valid range`() {
        // Arrange: Set input to values outside the valid range
        setInput("15\n5\n")

        // Act: Call the method with minValue and maxValue parameters
        val result = gameInputHandler.readGridSize("Enter grid size:", minValue = 3, maxValue = 10)

        // Assert: Check that the result is the valid input within the range
        assertEquals(5, result)
    }

    @Test
    fun `test readGridSize with invalid and then valid input`() {
        // Arrange: Set input to invalid value followed by a valid value
        setInput("invalid\n12\n8\n")

        // Act: Call the method with minValue and maxValue parameters
        val result = gameInputHandler.readGridSize("Enter grid size:", minValue = 3, maxValue = 10)

        // Assert: Check that the result is the valid input within the range
        assertEquals(8, result)
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

    @Test
    fun testReadKeySuccessful() {
        val input: InputStream = ByteArrayInputStream("a".toByteArray())
        val handler = GameInputHandler(input)

        val result = handler.readKey()
        assertEquals('a', result, "Expected 'a' but got $result")
    }

    /*
    @Test
    fun testReadKeyEmptyStream() {
        val input: InputStream = ByteArrayInputStream("".toByteArray())
        val handler = GameInputHandler(input)

        val result = handler.readKey()
        assertEquals(' ', result, "Expected space character but got $result")
    }
     */

    @Test
    fun testReadKeyException() {
        val input: InputStream = object : InputStream() {
            override fun read(): Int {
                throw RuntimeException("Test Exception")
            }
        }
        val handler = GameInputHandler(input)

        val result = handler.readKey()
        assertEquals(' ', result, "Expected space character but got $result")
    }

    @Test
    fun `test readGridSize with input below the minimum value`() {
        // Arrange: Set input to a value below the minimum value
        setInput("1\n5\n")

        // Act: Call the method with minValue and maxValue parameters
        val result = gameInputHandler.readGridSize("Enter grid size:", minValue = 3, maxValue = 10)

        // Assert: Check that the result is the valid input within the range
        assertEquals(5, result)
    }

    @Test
    fun `test readGridSize with input above the maximum value`() {
        // Arrange: Set input to a value above the maximum value
        setInput("15\n5\n")

        // Act: Call the method with minValue and maxValue parameters
        val result = gameInputHandler.readGridSize("Enter grid size:", minValue = 3, maxValue = 10)

        // Assert: Check that the result is the valid input within the range
        assertEquals(5, result)
    }
}
