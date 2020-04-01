import TrainEvent.TrainFinish
import TrainEvent.TrainStart
import kotlin.math.max

class Solver {
    private val dynamic = HashMap<Int, Int>()
    private var maximum: Int = 0

    fun solve(trains: List<Train>): Int {
        val events = trains
            .flatMap {
                listOf(
                    TrainStart(it.number, it.start, it.cost),
                    TrainFinish(it.number, it.start + it.time)
                )
            }
            .sortedWith(
                Comparator { a, b ->
                    if (a.time == b.time) {
                        if (a is TrainStart) 1 else -1
                    } else {
                        a.time - b.time
                    }
                }
            )
        events.forEach {
            when (it) {
                is TrainStart -> dynamic[it.number] = it.cost + maximum
                is TrainFinish -> maximum = max(maximum, dynamic[it.number]!!)
            }
        }
        return maximum
    }
}
