package subject

abstract class SubjectImpl<Context> : Subject<Context> {

    private val signal: Signal<Context> = Signal()

    override fun registerObserver(slot: (Subject<Context>, Context) -> Unit, index: Int) {
        signal.addCallback(slot)
    }

    override fun removeObserver(index: Int) {
        signal.removeCallback(index)
    }

    override fun notifyObservers(context: Context) {
        signal.emit(this, context)
    }

    protected fun contextChanged(context: Context) {
        notifyObservers(context)
    }

    override fun hashCode(): Int = signal.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SubjectImpl<*>

        if (signal != other.signal) return false

        return true
    }
}