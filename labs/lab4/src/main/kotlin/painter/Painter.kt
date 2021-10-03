package painter

import canvas.ICanvas
import picture.PictureDraft

class Painter {

    fun drawPictures(draft: PictureDraft, canvas: ICanvas) {
        draft.getShapeIterator().forEach { shape ->
            canvas.setColor(shape.getColor())
            shape.draw(canvas)
        }
    }
}