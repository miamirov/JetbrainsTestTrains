import TrainEvent.TrainFinish
import TrainEvent.TrainStart
import java.util.PriorityQueue

class Solver {
    private val dynamic = HashMap<Int, Int>()
    private val heap = PriorityQueue<Int>(1) { a, b -> b - a }

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
        heap.add(0)
        events.forEach {
            if (it is TrainStart) {
                dynamic[it.number] = it.cost + heap.peek()
            }
            if (it is TrainFinish)
                heap.add(dynamic[it.number])
        }
        return dynamic.values.max()!!.also {
            heap.clear()
            dynamic.clear()
        }
    }
}

fun main() {
    print(
        Solver().solve(
            listOf(
                Train(4, 34, 19, 1000),
                Train(3, 34, 20, 1021),
                Train(2, 12, 11, 600),
                Train(1, 12, 11, 570)
            )
        )
    )
}