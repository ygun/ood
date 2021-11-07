package state

import gumball.machine.IGumballMachine
import java.io.PrintStream

class SoldOutState(
    private val machine: IGumballMachine,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() = output.println("You can't insert a quarter, the machine is sold out")

    override fun ejectQuarter() = output.println("You can't eject, you haven't inserted a quarter yet")

    override fun turnCrank() = output.println("You turned but there's no gumballs")

    override fun dispense() = output.println("No gumball dispensed")

    override fun toString() = "sold out"
}