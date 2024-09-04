package com.rohan.minesweeper.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class MessagesTest {

    @Test
    fun `test get method with valid key`() {
        val expectedMessage = "Enter the size of the grid (e.g. 4 for a 4x4 grid):" // This should match the value in messages_en.properties
        val actualMessage = Messages.get("grid_size_prompt")
        assertEquals(expectedMessage, actualMessage)
    }

    @Test
    fun `test get method with invalid key`() {
        val exception = assertThrows<MissingResourceException> {
            Messages.get("non_existent_key")
        }
        assertEquals("Can't find resource for bundle java.util.PropertyResourceBundle, key non_existent_key", exception.message)
    }
}