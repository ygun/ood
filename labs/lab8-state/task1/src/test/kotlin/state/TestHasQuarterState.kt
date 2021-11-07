package state

import gumball.machine.GumballMachine
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestHasQuarterState {

    @Test
    fun `insertQuarter doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setHasQuarterState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.insertQuarter()
        assertTrue(machine.getState() is HasQuarterState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter change state to NoQuarter`() {
        val machine = GumballMachine(10)
        machine.setHasQuarterState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.ejectQuarter()
        assertTrue(machine.getState() is NoQuarterState)
        assertNotEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `turnCrank able to change state to SoldState`() {
        val machine = GumballMachine(1)
        machine.setHasQuarterState()
        val prevState = machine.getState()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.turnCrank()
        assertTrue(machine.getState() is SoldState)
        assertNotEquals(prevState, machine.getState())
        assertEquals(0, machine.getBallCount())
    }

    @Test
    fun `turnCrank able to change state to NoQuarter`() {
        val machine = GumballMachine(10)
        machine.setHasQuarterState()
        val prevState = machine.getState()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.turnCrank()
        assertTrue(machine.getState() is NoQuarterState)
        assertNotEquals(prevState, machine.getState())
        assertEquals(9, machine.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setHasQuarterState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.dispense()
        assertTrue(machine.getState() is HasQuarterState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }
}