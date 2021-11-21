package observer

import subject.Subject

interface Observer<T> {
    fun update(subject: Subject<T>, context: T)
}