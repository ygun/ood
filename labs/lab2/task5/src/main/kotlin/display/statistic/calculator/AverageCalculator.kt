package display.statistic.calculator

interface AverageCalculator {
    fun addNewValue(newValue: Double)
    fun calculateAverage(): Double
}