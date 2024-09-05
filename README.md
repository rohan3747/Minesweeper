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
  * Java Development Kit (JDK) (version 17 or later)
  * Gradle (version 8 or later)
  * Kotlin (optional for local development)

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
      
  * Alternatively, build and run a JAR file using Gradle with Kotlin DSL
    * Clone the Repository:
      * git clone https://github.com/rohan37/Minesweeper.git
      * cd project-name
    * Build the JAR: To build the JAR file, run the following Gradle command:
      * ./gradlew build
    * JAR File Location: After the build completes, the JAR file will be located at:
      * build/libs/Minesweeper-1.0.0.jar
    * Run the JAR
      * java -jar build/libs/Minesweeper-1.0.0.jar

### Troubleshooting ###
 * Compilation Errors: Check for correct JDK and Kotlin versions, and look for typos or syntax errors.
 * Runtime Errors: Verify input correctness and format. Check for logical errors or edge cases.

### Contributions ###
 * Feel free to contribute by submitting pull requests or opening issues. Your feedback and improvements are welcome!

