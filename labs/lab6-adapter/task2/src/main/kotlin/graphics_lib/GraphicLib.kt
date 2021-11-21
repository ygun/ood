package graphics_lib

import java.io.PrintStream

interface ICanvas {
    fun moveTo(x: Int, y: Int)
    fun lineTo(x: Int, y: Int)
}

class Canvas(private val output: PrintStream = System.out) : ICanvas {
    override fun moveTo(x: Int, y: Int) = output.println("moveTo ($x, $y)")

    override fun lineTo(x: Int, y: Int) = output.println("lineTo ($x, $y)")
}