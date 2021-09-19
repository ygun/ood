package observer.statistic

import observer.DisplayElement
import observer.Observer
import util.Context
import util.ContextFields

abstract class StatisticsDisplay(
    private val observableField: ContextFields
) : Observer, DisplayElement {

    private var maxValueIn = Float.MIN_VALUE
    private var minValueIn = Float.MAX_VALUE
    private var valueSumIn = 0f
    private var numReadingsIn = 0

    private var maxValueOut = Float.MIN_VALUE
    private var minValueOut = Float.MAX_VALUE
    private var valueSumOut = 0f
    private var numReadingsOut = 0

    override fun updateIn(context: Context) {
        val contextField = defineField(context)
        numReadingsIn++

        valueSumIn += contextField

        if (contextField > maxValueIn) {
            maxValueIn = contextField
        }
        if (contextField < minValueIn) {
            minValueIn = context.temperature
        }

        display()
    }

    override fun updateOut(context: Context) {
        val contextField = defineField(context)
        numReadingsOut++

        valueSumOut += contextField

        if (contextField > maxValueOut) {
            maxValueOut = contextField
        }
        if (contextField < minValueOut) {
            minValueOut = context.temperature
        }

        display()
    }

    private fun defineField(context: Context): Float {
        return when (observableField) {
            ContextFields.TEMPERATURE -> context.temperature
            ContextFields.HUMIDITY -> context.humidity
            ContextFields.PRESSURE -> context.pressure
        }
    }

    override fun display() {
        print("Avg/Max/Min ${observableField} Input: ${valueSumIn / numReadingsIn}/$maxValueIn/$minValueIn; ")
        println("Avg/Max/Min ${observableField} Output: ${valueSumOut / numReadingsOut}/$maxValueOut/$minValueOut")
    }
}