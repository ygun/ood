import {Dispatch} from "react"
import {store} from "../store/store"
import {END_MOVE_ELEMENTS} from "../store/actionTypes"
import {getSelectedHTMLElements} from "./getSelectedHTMLElements"

export const endMoveElement = (isMoveElements: boolean, dispatch: Dispatch<any>) => {
    const editor = store.getState()

    isMoveElements = false
    const payload = new Map()
    let isMoved: boolean = true
    const elements = new Map()

    const selectedHTMLElements = getSelectedHTMLElements(editor)
    selectedHTMLElements.forEach(elem => {
        editor.elements.forEach(e => {
            if (!(elem && e.id === elem.id)) return

            let topLeftPointX = 0
            let topLeftPointY = 0
            if (elem && e.id === elem.id) {
                let parent = elem.parentNode as HTMLElement
                if (elem.tagName === 'P') {
                    parent = parent.parentNode as HTMLElement
                }

                const X = parent.getAttribute('x')
                const Y = parent.getAttribute('y')
                if (X !== null && X !== undefined) {
                    topLeftPointX = parseFloat(X.slice(0, -1))
                }

                if (Y !== null && Y !== undefined) {
                    topLeftPointY = parseFloat(Y.slice(0, -1))
                }
            }
            const bottomRightPointX = Math.round((Math.round((e.bottomRightPoint.x - e.topLeftPoint.x) * 100) / 100 + topLeftPointX) * 100) / 100
            const bottomRightPointY = Math.round((Math.round((e.bottomRightPoint.y - e.topLeftPoint.y) * 100) / 100 + topLeftPointY) * 100) / 100
            const newTopLeftPoint = new Map([
                ["x", topLeftPointX],
                ["y", topLeftPointY]
            ])
            const newBottomRightPoint = new Map([
                ["x", bottomRightPointX],
                ["y", bottomRightPointY]
            ])
            const newCenter = new Map([
                ["x", e.center.x],
                ["y", e.center.y]
            ])
            const newPos = new Map([
                ["newTopLeftPoint", newTopLeftPoint],
                ["newBottomRightPoint", newBottomRightPoint],
                ["newCenter", newCenter]
            ])
            elements.set(e.id, newPos)
            isMoved = !(topLeftPointX === e.topLeftPoint.x && topLeftPointY === e.topLeftPoint.y)
        })
    })

    payload.set('elements', elements)

    if (isMoved) {
        dispatch({type: END_MOVE_ELEMENTS, payload: payload})
    }

    return isMoveElements
}