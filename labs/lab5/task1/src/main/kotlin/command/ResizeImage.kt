package command

import document.IDocument
import document.element.image.IImage

class ResizeImage(
    private val image: IImage,
    private val newWidth: Int,
    private val newHeight: Int
) : ICommand {

    init {
        if (!isSizeValid()) {
            throw IllegalArgumentException("Not valid size of image. Size cannot be more than 10000 and less than 1")
        }
    }

    private fun isSizeValid() = newHeight in 1..9999 && newWidth in 1..9999

    private val oldWidth: Int = image.getWidth()
    private val oldHeight: Int = image.getHeight()

    override fun execute(document: IDocument) = image.resize(newWidth, newHeight)

    override fun revert(document: IDocument) = image.resize(oldWidth, oldHeight)

    override fun dispose() = Unit
}