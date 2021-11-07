package state

import machine.IGumballMachine
import java.io.PrintStream

class SoldState(
    private val machine: IGumballMachine,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() = output.println("Please wait, we're already giving you a gumball")

    override fun ejectQuarter() {
        if (machine.getCountQuarters() != 0) {
            output.println("${machine.getCountQuarters()} quarters returned")
            machine.removeAllQuarters()
        } else {
            output.println("You can't eject, you haven't inserted a quarter yet")
        }
    }

    override fun turnCrank() = output.println("Turning twice doesn't get you another gumball")

    override fun dispense() {
        machine.releaseBall()
        if (machine.getBallCount() == 0) {
            output.println("Oops, out of gumballs")
            machine.setSoldOutState()
            return
        }

        if (machine.getCountQuarters() == 0) {
            machine.setNoQuarterState()
        } else {
            machine.setHasQuarterState()
        }
    }

    override fun toString() = "delivering a gumball"
}