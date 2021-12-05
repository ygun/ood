import {store} from "../store/store"
import {getSelectedHTMLElements} from "./getSelectedHTMLElements"

export const isMoveElement = (event: any) => {
    let isMoveElements = false
    const editor = store.getState()
    const selectedElements = getSelectedHTMLElements(editor)

    let isSelectedElements = []
    for (let i = 0; i < selectedElements.length; i++) {
        const element = selectedElements[i]
        if (!element) continue

        if (element.tagName === 'P') {
            const parent = element.parentNode as HTMLElement
            const shiftX = event.pageX - element.getBoundingClientRect().left
            const shiftY = event.pageY - element.getBoundingClientRect().top
            const parentSize = {
                width: parent.getBoundingClientRect().width,
                height: parent.getBoundingClientRect().height
            }

            isSelectedElements.push(
                event.target === parent ||
                (event.target.tagName === 'P' && (parentSize.width - shiftX <= 5 || parentSize.height - shiftY <= 5 || shiftX <= 5 || shiftY <= 5)))
        } else {
            isSelectedElements.push(event.target === selectedElements[i] || (selectedElements[i] as HTMLElement)
                .contains(event.target as Node))
        }
    }

    if (isSelectedElements.includes(true)) {
        isMoveElements = true
    }

    return isMoveElements
}