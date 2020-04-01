data class Train(val number: Int, val start: Int, val time: Int, val cost: Int)

sealed class TrainEvent {
    abstract val time: Int

    data class TrainStart(val number: Int, override val time: Int, val cost: Int) : TrainEvent()
    data class TrainFinish(val number: Int, override val time: Int) : TrainEvent()
}
