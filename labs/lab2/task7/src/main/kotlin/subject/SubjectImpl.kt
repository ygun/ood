package subject

import observer.Observer
import observer.ObserverWithPriority
import util.DataClass
import java.util.*

abstract class SubjectImpl<Context : DataClass>(fields: Set<String>) : Subject<Context> {

    private val observers: SortedMap<String, SortedSet<ObserverWithPriority<Context>>> = TreeMap()

    private var currContext: Context? = null;

    init {
        fields.forEach {
            observers.putIfAbsent(it, TreeSet())
        }
    }

    override fun registerObserver(observer: Observer<Context>, priority: Int, observableFields: Set<String>) {
        observableFields.forEach {
            if (!observers.getValue(it).add(ObserverWithPriority(observer, priority)))
                throw IllegalArgumentException("Field: $it; does not exist in Subject")
        }
    }

    override fun removeObserver(observer: Observer<Context>, observableFields: Set<String>) {
        try {
            observableFields.forEach {
                observers.getValue(it).remove(ObserverWithPriority(observer, 0))
            }
        } catch (e: NoSuchElementException) {
            throw e
        }
    }

    override fun notifyObservers(context: Context, observableFields: Set<String>) {
        val notifiedObservers = mutableSetOf<Observer<Context>>()
        observableFields.forEach { field ->
            observers.getValue(field).toSortedSet().forEach {
                if (it.observer !in notifiedObservers) {
                    notifiedObservers.add(it.observer)
                    it.observer.update(context)
                }
            }
        }
    }

    protected fun contextChanged(context: Context) {
        val changedFields = mutableSetOf<String>()
        if (currContext == null) {
            currContext = context
            context.getFieldNames().forEach(changedFields::add)
        } else {
            context.getFieldNames().forEach {
                val contextValue = context.getFieldByName(it)
                val currValue = currContext!!.getFieldByName(it)
                if (currValue != contextValue) {
                    changedFields.add(it)
                    currContext!!.updateFieldByName(it, contextValue)
                }
            }
        }
        notifyObservers(context, changedFields)
    }
}