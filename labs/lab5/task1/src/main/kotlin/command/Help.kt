package command

import util.CommandType
import document.IDocument
import java.io.PrintStream

class Help(private val output: PrintStream) : ICommand {

    override fun execute(document: IDocument) {
        output.println("List of all commands and their arguments:")
        CommandType.values().forEach {
            println("\t" + it.getCommandWithArgs())
        }
    }

    override fun revert(document: IDocument) = Unit

    override fun dispose() = Unit
}