package com.rohan.minesweeper

/**
 * Entry point for the Minesweeper game.
 */
fun main() {
    val inputHandler = GameInputHandler()
    val gameRunner = MinesweeperGameRunner(inputHandler)
    gameRunner.run()
}

class MinesweeperGameRunner(
    private val inputHandler: GameInputHandler,
    private val messages: Messages = Messages
) {

    fun run() {
        while (true) {
            println(messages.get("welcome_message"))

            val size = inputHandler.readGridSize(messages.get("grid_size_prompt"))
            val numMines = inputHandler.readMineCount(messages.get("mine_count_prompt"), size)
                .coerceAtMost((size * size * 0.35).toInt())

            val game = Minesweeper(size, numMines)
            val controller = GameController(game, inputHandler)
            controller.play()

            inputHandler.readString(messages.get("play_again_prompt"))
        }
    }
}