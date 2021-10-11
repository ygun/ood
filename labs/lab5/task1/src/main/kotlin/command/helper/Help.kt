package command.helper

import command.ICommand
import util.CommandType
import document.IDocument
import java.io.PrintStream

class Help : ICommand, IHelperCommand {
    override fun execute(document: IDocument) = Unit
    override fun revert(document: IDocument) = Unit

    override fun executeHelper(document: IDocument, output: PrintStream) {
        output.println("List of all commands and their arguments:")
        CommandType.values().forEach {
            println("\t" + it.getCommandWithArgs())
        }
    }

    override fun dispose() = Unit
}