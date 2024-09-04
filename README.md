# Minesweeper
This Minesweeper game is implemented in Kotlin and follows Object-Oriented Programming (OOP) principles and the SOLID design principles to ensure maintainability, readability, and extensibility. The game features a grid where the player can reveal cells to find mines and win the game by uncovering all non-mine cells.

### Design and Assumptions ###

## Design ##
* Classes and Responsibilities:
  * Minesweeper: Manages game state and provides methods to interact with the game.
  * Grid: Manages the grid of cells, including mine placement and cell revealing.
  * Cell: Represents a single cell in the grid with properties like mine status, revealed state, and adjacent mine count.
  * GameUtils: Provides utility functions for user input and parsing.
  * GameController: Manages the game flow and user interactions with the Minesweeper game.
  
* Assumptions:
  * Grid size and number of mines are specified by the user at runtime.
  * User input is validated for correct format and range.


### Compilation and Execution ###

* Prerequisites
  * Kotlin Compiler: Ensure Kotlin is installed on your system. Download it from Kotlin's official website.
  * Java Development Kit (JDK): Requires JDK 8 or higher. Ensure JDK is installed and configured.


## Installation ##

 * Clone the Repository:
    * git clone https://github.com/rohan37/Minesweeper.git
      cd Minesweeper
 * Build the Project:
    *  Ensure Gradle is installed. Install it from Gradle's official website.
    *   Build the project using:
        gradle build


## Running the Application ##
  * Compile and Run:
    * Navigate to the project directory.
    * Execute the application using: 
      * ./gradlew clean build
      * ./gradlew run
    * Alternatively, compile and run manually with the Kotlin compiler:
      * kotlinc src -d out
      * java -cp out com.rohan.minesweeper.AppKt

## Environment ##
 * Operating Systems:
    * Windows: Ensure compatible versions of JDK and Kotlin are installed.
    * Linux: Use package managers like apt or yum for installation.
    * macOS: Use Homebrew for installation.

### Troubleshooting ###
 * Compilation Errors: Check for correct JDK and Kotlin versions, and look for typos or syntax errors.
 * Runtime Errors: Verify input correctness and format. Check for logical errors or edge cases.

### Contributions ###
 * Feel free to contribute by submitting pull requests or opening issues. Your feedback and improvements are welcome!

