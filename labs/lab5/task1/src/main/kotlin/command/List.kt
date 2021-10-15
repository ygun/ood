package command

import document.IDocument
import java.io.PrintStream

class List(private val output: PrintStream) : ICommand {

    override fun execute(document: IDocument) {
        output.println("Title: ${document.getTitle()}")
        (0 until document.getItemsCount()).forEach {
            output.println("\t$it: ${document.getItem(it)}")
        }
    }

    override fun revert(document: IDocument) = Unit

    override fun dispose() = Unit
}