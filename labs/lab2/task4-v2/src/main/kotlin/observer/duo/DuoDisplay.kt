package observer.duo

import observer.simple.Observer

class DuoDisplay(
    private val inDisplay: Observer,
    private val outDisplay: Observer
) : DuoObserver {

    override fun displaySystemStatus() {
        print("Input display")
        inDisplay.display()

        print("Output display")
        outDisplay.display()
    }
}