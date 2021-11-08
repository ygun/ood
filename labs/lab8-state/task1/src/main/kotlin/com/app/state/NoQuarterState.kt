package com.app.state

import com.app.machine.IGumballMachinePrivate
import java.io.PrintStream

class NoQuarterState(
    private val machine: IGumballMachinePrivate,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() {
        output.println("You inserted a quarter")
        machine.setHasQuarterState()
    }

    override fun ejectQuarter() = output.println("You haven't inserted a quarter")

    override fun turnCrank() = output.println("You turned but there's no quarter")

    override fun dispense() = output.println("You need to pay first")

    override fun toString() = "waiting for quarter"
}