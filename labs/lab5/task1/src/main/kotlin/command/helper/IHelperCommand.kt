package command.helper

import document.IDocument
import java.io.PrintStream

interface IHelperCommand {
    fun executeHelper(document: IDocument, output: PrintStream = System.out)
}