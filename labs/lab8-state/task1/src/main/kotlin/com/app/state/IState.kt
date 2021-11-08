package com.app.state

interface IState {
    fun insertQuarter()
    fun ejectQuarter()
    fun turnCrank()
    fun dispense()
}