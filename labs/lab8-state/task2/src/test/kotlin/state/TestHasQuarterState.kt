package state

import machine.MultiGumballMachine
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestHasQuarterState {

    @Test
    fun `insertQuarter add quarter to Machine`() {
        val machine = MultiGumballMachine(10)
        machine.setHasQuarterState()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.insertQuarter()
        assertTrue(machine.getState() is HasQuarterState)
        assertEquals(1, machine.getCountQuarters())

        hasQuarterState.insertQuarter()
        hasQuarterState.insertQuarter()
        hasQuarterState.insertQuarter()
        hasQuarterState.insertQuarter()
        assertTrue(machine.getState() is HasQuarterState)
        assertEquals(5, machine.getCountQuarters())

        hasQuarterState.insertQuarter()
        assertTrue(machine.getState() is HasQuarterState)
        assertEquals(5, machine.getCountQuarters())
    }

    @Test
    fun `ejectQuarter change state to NoQuarter and return all quarters`() {
        val machine = MultiGumballMachine(10)
        machine.setHasQuarterState()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        assertEquals(3, machine.getCountQuarters())

        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.ejectQuarter()
        assertTrue(machine.getState() is NoQuarterState)
        assertNotEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
        assertEquals(0, machine.getCountQuarters())
    }

    @Test
    fun `turnCrank able to change state to SoldState`() {
        val machine = MultiGumballMachine(1)
        machine.setHasQuarterState()
        machine.addQuarter()
        val prevState = machine.getState()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.turnCrank()
        assertTrue(machine.getState() is SoldState)
        assertNotEquals(prevState, machine.getState())
        assertEquals(0, machine.getBallCount())
    }

    @Test
    fun `turnCrank able to change state to NoQuarter`() {
        val machine = MultiGumballMachine(10)
        machine.setHasQuarterState()
        machine.addQuarter()
        val prevState = machine.getState()

        val hasQuarterState = HasQuarterState(machine)

        hasQuarterState.turnCrank()
        assertTrue(machine.getState() is NoQuarterState)
        assertNotEquals(prevState, machine.getState())
        assertEquals(9, machine.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machine = MultiGumballMachine(10)
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