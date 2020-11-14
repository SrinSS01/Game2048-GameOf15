package board

class CreateGameBoard<T>(width: Int) : CreateSquareBoard(width), GameBoard<T> {
    private val board : MutableList<MutableList<Pair<Cell, T?>>> = mutableListOf()
    init {
        for (i in 0 until width){
            val row = mutableListOf<Pair<Cell, T?>>()
            for (j in 0 until width) {
                row.add(j, Cell(i + 1, j + 1) to null)
            }
            board.add(i, row)
        }
    }
    override fun get(cell: Cell): T? = board.flatten().find { it.first == cell }?.second
    override fun set(cell: Cell, value: T?) {
        board[cell.i - 1][cell.j - 1] = cell to value
    }
    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = board.flatten().filter { predicate(it.second) }.map { it.first }
    override fun find(predicate: (T?) -> Boolean): Cell? = board.flatten().find { predicate(it.second) }?.first
    override fun any(predicate: (T?) -> Boolean): Boolean = board.flatten().any { predicate(it.second) }
    override fun all(predicate: (T?) -> Boolean): Boolean = board.flatten().all { predicate(it.second) }
}