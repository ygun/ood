package document.converter

import document.IDocument

interface IConverter {
    fun convert(document: IDocument): String
}