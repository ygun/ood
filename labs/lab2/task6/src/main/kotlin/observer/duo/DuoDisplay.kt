package observer.duo

import observer.Observer

class DuoDisplay(
    private val inDisplay: Observer,
    private val outDisplay: Observer
) : DuoObserver {

    override fun displaySystemStatus() {
        print("Inside display: ")
        inDisplay.display()

        print("Outside display: ")
        outDisplay.display()
    }
}