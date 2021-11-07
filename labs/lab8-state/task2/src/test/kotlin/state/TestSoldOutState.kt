package state

import machine.MultiGumballMachine
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestSoldOutState {
    @Test
    fun `insertQuarter doesn't change state`() {
        val machine = MultiGumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)

        soldOutState.insertQuarter()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state`() {
        val machine = MultiGumballMachine(10)
        machine.setSoldOutState()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        assertEquals(3, machine.getCountQuarters())

        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)

        soldOutState.ejectQuarter()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
        assertEquals(0, machine.getCountQuarters())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machine = MultiGumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)

        soldOutState.turnCrank()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machine = MultiGumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)

        soldOutState.dispense()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }
}