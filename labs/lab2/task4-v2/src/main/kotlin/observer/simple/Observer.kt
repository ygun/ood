package observer.simple

import util.Context

interface Observer {
    fun update(context: Context)
    fun display()
}