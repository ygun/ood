import com.app.machine.GumballMachine

fun useGumballMachine(machine: GumballMachine)
{
    println(machine.toString())
    println()

    machine.fillMachine(10)

    println(machine.toString())
    println()

    machine.insertQuarter()
    machine.turnCrank()
    println()

    println(machine.toString())
    println()

    machine.insertQuarter()
    machine.ejectQuarter()
    machine.turnCrank()
    println()

    println(machine.toString())
    println()

    machine.insertQuarter()
    machine.turnCrank()
    println()

    machine.insertQuarter()
    machine.turnCrank()
    println()

    machine.ejectQuarter()
    println()

    println(machine.toString())
    println()
}

fun main() {
    val machine = GumballMachine(0)

    useGumballMachine(machine)
}