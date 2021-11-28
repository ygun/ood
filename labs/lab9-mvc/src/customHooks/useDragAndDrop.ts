import {mouseMoveElement} from "../functions/mouseMoveElement"
import {moveElementPoint, resizeElement} from "../functions/resizeElement"
import {endMoveElement} from "../functions/endMoveElement"
import {removeSelectOfElement} from "../functions/removeSelectOfElement"
import {isMoveElement} from "../functions/isMoveElement"
import {Dispatch} from "react"
import {useDispatch} from "react-redux"
import {store} from "../store/store"
import {CHANGE_POSITION_OF_ELEMENTS} from "../store/actionTypes"
import {useEventListener} from "./useEventListner"
import {endResizeElement} from "../functions/endResizeElement"


let isEventMoveElement: boolean
let firstPosX: number
let firstPosY: number
let isResize: boolean
let pointIndex: number
let resizePayload: any
let resized = false

export function useDragAndDrop() {
    const dispatch: Dispatch<any> = useDispatch()

    let handleMouseDown = (evt: MouseEvent) => {
        firstPosX = evt.clientX
        firstPosY = evt.clientY
        isEventMoveElement = isMoveElement(evt)

        pointIndex = resizeElement(evt, pointIndex)
        isResize = pointIndex >= 0

        removeSelectOfElement(evt, store.dispatch)
    }
    useEventListener('mousedown', handleMouseDown)


    let handleMouseMove = (evt: MouseEvent) => {
        if (isEventMoveElement) {
            mouseMoveElement(evt, firstPosX, firstPosY)
        }

        if (isResize) {
            resized = true
            resizePayload = moveElementPoint(evt, firstPosX, firstPosY, pointIndex)
        }
    }
    useEventListener('mousemove', handleMouseMove)


    let handleMouseUp = (evt: MouseEvent) => {
        if (isEventMoveElement) {
            isEventMoveElement = endMoveElement(isEventMoveElement, store.dispatch)
        }

        if (isResize) {
            isResize = false
            pointIndex = -1
            if (resized) {
                endResizeElement()
                if (!resizePayload.get('small')) {
                    dispatch({type: CHANGE_POSITION_OF_ELEMENTS, payload: resizePayload})
                }
            }
        }
    }
    useEventListener('mouseup', handleMouseUp)
}
