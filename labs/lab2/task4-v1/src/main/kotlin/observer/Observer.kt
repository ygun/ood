package observer

import util.Context

interface Observer {
    fun updateIn(context: Context)
    fun updateOut(context: Context)
}