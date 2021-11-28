import {Element} from "./Elements"
import {Color} from "./Color"

export type {
    Editor
}

type Editor = {
    id: string,
    elements: Array<Element>,
    background: Color | string,
    selectionElementsId: Array<string>
}
