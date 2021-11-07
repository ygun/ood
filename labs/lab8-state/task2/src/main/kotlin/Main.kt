import machine.MultiGumballMachine
import machine.IGumballMachine

fun useGumballMachine(machine: IGumballMachine)
{
    println(machine.toString())
    println()

    machine.fillMachine(10)

    println(machine.toString())
    println()

    machine.insertQuarter()
    machine.insertQuarter()
    machine.insertQuarter()

    println(machine.toString() + "\n")

    machine.turnCrank()
    println()

    println(machine.toString() + "\n")

    machine.insertQuarter()
    machine.ejectQuarter()
    machine.turnCrank()
    println()

    println(machine.toString() + "\n")

    machine.insertQuarter()
    machine.turnCrank()
    println()

    machine.insertQuarter()
    machine.turnCrank()
    println()

    machine.ejectQuarter()
    println()

    println(machine.toString() + "\n")
}

fun main() {
    val machine = MultiGumballMachine(0)

    useGumballMachine(machine)
}