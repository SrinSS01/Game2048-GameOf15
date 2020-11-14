package board
import board.Direction.*
open class CreateSquareBoard(final override val width: Int) : SquareBoard {
    private val boardCoords : MutableList<MutableList<Cell>> = mutableListOf()
    init {
        for (i in 0 until width){
            val row = mutableListOf<Cell>()
            for (j in 0 until width) {
                row.add(j, Cell(i + 1, j + 1))
            }
            boardCoords.add(i, row)
        }
    }
    override fun getCellOrNull(i: Int, j: Int): Cell? = boardCoords.getOrNull(i - 1)?.getOrNull(j - 1)
    override fun getCell(i: Int, j: Int): Cell = boardCoords[i - 1][j - 1]
    override fun getAllCells(): Collection<Cell> = boardCoords.flatten()
    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val cellsRow = boardCoords[i - 1].filter { it.j in jRange }
        return if (jRange.first < jRange.last) cellsRow else cellsRow.asReversed()
    }
    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val cellsColumn = boardCoords.flatten().filter { it.j == j && it.i in iRange }
        return if (iRange.first < iRange.last) cellsColumn else cellsColumn.asReversed()
    }
    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction) {
            UP -> getCellOrNull(i - 1, j)
            DOWN -> getCellOrNull(i + 1, j)
            LEFT -> getCellOrNull(i, j - 1)
            RIGHT -> getCellOrNull(i, j + 1)
        }
    }
}