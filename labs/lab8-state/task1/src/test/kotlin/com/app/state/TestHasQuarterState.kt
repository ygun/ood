package com.app.state

import com.app.machine.GumballMachineImpl
import org.junit.jupiter.api.Test
import com.app.state.HasQuarterState
import com.app.state.NoQuarterState
import com.app.state.SoldState
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestHasQuarterState {

    @Test
    fun `insertQuarter doesn't change state`() {
        val machineImpl = GumballMachineImpl()
        machineImpl.setHasQuarterState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.insertQuarter()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `ejectQuarter change state to NoQuarter`() {
        val machineImpl = GumballMachineImpl()
        machineImpl.setHasQuarterState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.ejectQuarter()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `turnCrank able to change state to SoldState`() {
        val machineImpl = GumballMachineImpl()
        machineImpl.setHasQuarterState()
        val prevState = machineImpl.getState()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.turnCrank()
        assertTrue(machineImpl.getState() is SoldState)
        assertNotEquals(prevState, machineImpl.getState())
        assertEquals(0, machineImpl.getBallCount())
    }

    @Test
    fun `turnCrank able to change state to NoQuarter`() {
        val machineImpl = GumballMachineImpl(10)
        machineImpl.setHasQuarterState()
        val prevState = machineImpl.getState()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.turnCrank()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertEquals(9, machineImpl.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machineImpl = GumballMachineImpl(10)
        machineImpl.setHasQuarterState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.dispense()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }
}