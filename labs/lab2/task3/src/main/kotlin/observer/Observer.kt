package observer

interface Observer<T> {
    fun update(context: T)
}