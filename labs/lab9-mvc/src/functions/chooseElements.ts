import {Editor} from '../entities/Editor'

export const chooseElements = (editor: Editor, listElementsId: Array<string>): Editor => ({
    ...editor,
    selectionElementsId: listElementsId
})