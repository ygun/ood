import {mouseMoveElement} from "../functions/mouseMoveElement"
import {moveElementPoint, resizeElement} from "../functions/resizeElement"
import {endMoveElement} from "../functions/endMoveElement"
import {removeSelectOfElements} from "../functions/removeSelectOfElements"
import {isMoveElement} from "../functions/isMoveElement"
import {Dispatch} from "react"
import {useDispatch} from "react-redux"
import {store} from "../store/store"
import {CHANGE_POSITION_OF_ELEMENTS} from "../store/actionTypes"
import {useEventListener} from "./useEventListner"
import {endResizeElement} from "../functions/endResizeElement"


let isMoveElementEvent: boolean
let firstPosX: number
let firstPosY: number
let isResizeAction: boolean
let draggedPointIndex: number
let resizePayload: any
let wasResizeAction = false

export const useDragAndDrop = () => {
    const dispatch: Dispatch<any> = useDispatch()

    const handleMouseDown = (evt: MouseEvent) => {
        firstPosX = evt.clientX
        firstPosY = evt.clientY
        isMoveElementEvent = isMoveElement(evt)

        draggedPointIndex = resizeElement(evt, draggedPointIndex)
        isResizeAction = draggedPointIndex >= 0

        removeSelectOfElements(evt, store.dispatch)
    }
    useEventListener('mousedown', handleMouseDown)


    const handleMouseMove = (evt: MouseEvent) => {
        if (isMoveElementEvent) {
            mouseMoveElement(evt, firstPosX, firstPosY)
        }

        if (isResizeAction) {
            wasResizeAction = true
            resizePayload = moveElementPoint(evt, firstPosX, firstPosY, draggedPointIndex)
        }
    }
    useEventListener('mousemove', handleMouseMove)


    const handleMouseUp = () => {
        if (isMoveElementEvent) {
            isMoveElementEvent = endMoveElement(isMoveElementEvent, store.dispatch)
        }

        if (isResizeAction) {
            isResizeAction = false
            draggedPointIndex = -1
            if (wasResizeAction) {
                endResizeElement()
                if (!resizePayload.get('small')) {
                    dispatch({type: CHANGE_POSITION_OF_ELEMENTS, payload: resizePayload})
                }
            }
        }
    }
    useEventListener('mouseup', handleMouseUp)
}
