package observer

import util.Context

interface Observer {
    fun update(context: Context)
}