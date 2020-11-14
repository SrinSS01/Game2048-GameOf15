package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = CreateSquareBoard(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = CreateGameBoard(width)

