package command.helper

import command.ICommand
import document.IDocument
import java.io.PrintStream

class List : ICommand, IHelperCommand {
    override fun execute(document: IDocument) = Unit
    override fun revert(document: IDocument) = Unit

    override fun executeHelper(document: IDocument, output: PrintStream) {
        output.println("Title: ${document.getTitle()}")
        (0 until document.getItemsCount()).forEach {
            output.println("\t$it: ${document.getItem(it)}")
        }
    }

    override fun dispose() = Unit
}