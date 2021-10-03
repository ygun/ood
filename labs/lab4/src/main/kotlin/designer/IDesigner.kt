package designer

import picture.PictureDraft
import java.io.InputStream

interface IDesigner {
    fun createDraft(inputStream: InputStream = System.`in`): PictureDraft
}