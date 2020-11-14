package games.gameOfFifteen

import board.Direction
import board.Direction.*
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOf15(initializer)

class GameOf15(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)
    override fun initialize() {
        val cells = board.getAllCells()
        val initialValues = initializer.initialPermutation
        cells.forEachIndexed {
            index, cell ->
            if (index < 15)
                board[cell] = initialValues[index]
        }
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean {
        if (board[board.getCell(4, 4)] != null) return false
        val cellsValues = board.getAllCells().mapNotNull { board[it] }
        for (i in 0 until cellsValues.size - 1)
            if (cellsValues[i] > cellsValues[i + 1]) return false
        return true
    }
    override fun processMove(direction: Direction) {
        board.moveValue(direction)
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

    private fun GameBoard<Int?>.moveValue(direction: Direction){
        val nullCell = getAllCells().find { get(it) == null }
        nullCell?.run {
            when (direction) {
                UP -> {
                    getNeighbour(DOWN)?.let {
                        set(this, board[it])
                        set(it, null)
                    }
                }
                DOWN -> {
                    getNeighbour(UP)?.let {
                        set(this, board[it])
                        set(it, null)
                    }
                }
                LEFT -> {
                    getNeighbour(RIGHT)?.let {
                        set(this, board[it])
                        set(it, null)
                    }
                }
                RIGHT -> {
                    getNeighbour(LEFT)?.let {
                        set(this, board[it])
                        set(it, null)
                    }
                }
            }
        }
    }

}

