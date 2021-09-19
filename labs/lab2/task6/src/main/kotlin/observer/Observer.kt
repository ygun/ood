package observer

import context.Context

interface Observer {
    fun update(context: Context)
    fun display()
}