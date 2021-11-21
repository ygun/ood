package document.converter

import document.IDocument
import document.item.DocumentItem
import kotlin.io.path.name

class HtmlConverter : IConverter {

    override fun convert(document: IDocument): String {
        var items = ""
        (0 until document.getItemsCount()).forEach {
            items += "\t" + getDocumentItemAsHtml(document.getItem(it)) + "\n\t<br>\n"
        }
        return "<!DOCTYPE html>\n" +
                "<html lang=\"\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<title>" + document.getTitle() + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                    items +
                "</body>\n" +
                "</html>"
    }

    private fun getDocumentItemAsHtml(item: DocumentItem): String {
        if (item.image != null) {
            return "<img src=\"${
                "images/" + item.image!!.getPath().name
            }\" width=\"${item.image!!.getWidth()}\" height=\"${item.image!!.getHeight()}\">"
        }
        if (item.paragraph != null) {
            return "<p>${item.paragraph!!.getText()}</p>"
        }
        throw IllegalArgumentException("This item cannot be represent as HTML tag")
    }
}