package graphics_lib

import java.io.PrintStream

interface ICanvas {
    fun setColor(color: UInt)
    fun moveTo(x: Int, y: Int)
    fun lineTo(x: Int, y: Int)
}

class Canvas(private val output: PrintStream = System.out) : ICanvas {

    override fun setColor(color: UInt)  = output.println("setColor $color")

    override fun moveTo(x: Int, y: Int) = output.println("moveTo ($x, $y)")

    override fun lineTo(x: Int, y: Int) = output.println("lineTo ($x, $y)")
}