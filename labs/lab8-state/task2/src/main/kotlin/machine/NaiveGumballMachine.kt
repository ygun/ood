package machine

import state.*
import state.States.*
import java.io.PrintStream

class NaiveGumballMachine(
    private var countBalls: Int = 0,
    private val output: PrintStream = System.out
) : IGumballMachine {

    private var countQuarters: Int = 0
    private var state: States = SOLD_OUT

    init {
        if (countBalls > 0) {
            setNoQuarterState()
        }
    }

    override fun insertQuarter() {
        when (state) {
            SOLD_OUT -> output.println("You can't insert a quarter, the machine is sold out")
            NO_QUARTER -> {
                output.println("You inserted a quarter")
                setHasQuarterState()
                addQuarter()
            }
            HAS_QUARTER -> {
                if (getCountQuarters() >= IGumballMachine.MAX_QUARTERS_COUNT) {
                    output.println("You can't insert quarters more than ${IGumballMachine.MAX_QUARTERS_COUNT}")
                } else {
                    output.println("You insert quarter, now machine has ${getCountQuarters()} quarters")
                    addQuarter()
                }
            }
            SOLD -> output.println("Please wait, we're already giving you a gumball")
        }
    }

    override fun ejectQuarter() {
        when (state) {
            SOLD_OUT -> soldEject()
            NO_QUARTER -> output.println("You haven't inserted a quarter")
            HAS_QUARTER -> {
                output.println("${getCountQuarters()} quarters returned")
                removeAllQuarters()
                setNoQuarterState()
            }
            SOLD -> soldEject()
        }
    }

    override fun turnCrank() {
        when (state) {
            SOLD_OUT -> output.println("You turned but there's no gumballs")
            NO_QUARTER -> output.println("You turned but there's no quarter")
            HAS_QUARTER -> {
                if (getCountQuarters() <= 0) {
                    println("Please insert a quarters")
                    return
                }

                output.println("You turned...")
                releaseBall()

                if (countBalls == 0) {
                    state = SOLD
                } else if (countQuarters == 0) {
                    state = NO_QUARTER
                }
            }
            SOLD -> output.println("Turning twice doesn't get you another gumball")
        }
    }

    override fun dispense() {
        when (state) {
            SOLD_OUT -> output.println("No gumball dispensed")
            NO_QUARTER -> output.println("You need to pay first")
            HAS_QUARTER -> output.println("No gumball dispensed")
            SOLD -> {
                releaseBall()
                if (getBallCount() == 0) {
                    output.println("Oops, out of gumballs")
                    setSoldOutState()
                    return
                }

                if (getCountQuarters() == 0) {
                    setNoQuarterState()
                } else {
                    setHasQuarterState()
                }
            }
        }
    }

    override fun releaseBall() {
        if (countBalls != 0 && countQuarters > 0) {
            output.println("A gumball comes rolling out the slot...")
            --countBalls
            --countQuarters
        }
    }

    override fun getBallCount(): Int = countBalls

    override fun fillMachine(ballsCount: Int) {
        countBalls = ballsCount
        if (countBalls > 0) {
            state = NO_QUARTER
        }
    }

    override fun getCountQuarters(): Int = countQuarters

    override fun addQuarter() {
        countQuarters++
    }

    override fun removeAllQuarters() {
        countQuarters = 0
    }

    override fun setHasQuarterState() {
        state = HAS_QUARTER
    }

    override fun setNoQuarterState() {
        state = NO_QUARTER
    }

    override fun setSoldState() {
        state = SOLD
    }

    override fun setSoldOutState() {
        state = SOLD_OUT
    }

    override fun toString(): String {
        return "Mighty Gumball, Inc.\n" +
                "Inventory: $countBalls gumballs\n" +
                "Count quarters: $countQuarters\n" +
                "Machine is $state"
    }

    fun getState(): States = state

    private fun soldEject() {
        if (countQuarters != 0) {
            output.println("$countQuarters quarters returned")
            countQuarters = 0
        } else {
            output.println("You can't eject, you haven't inserted a quarter yet")
        }
    }
}