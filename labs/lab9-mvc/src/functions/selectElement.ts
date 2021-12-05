import {Dispatch} from "react"
import {CHOOSE_ELEMENTS} from "../store/actionTypes"

export const selectElements = (event: any, id: string, dispatch: Dispatch<any>) => {
    const clickedElem = event.currentTarget
    const chosenClassName = 'element_chosen'

    if (event.ctrlKey) {
        multipleSelection(clickedElem, chosenClassName, dispatch)
    } else {
        singleElemSelection(clickedElem, chosenClassName, dispatch, id)
    }
}

const multipleSelection = (clickedElem: any, chosenClassName: string, dispatch: (value: any) => void) => {
    if (!clickedElem.getAttribute('data-is-element')) return

    if (clickedElem.classList.contains(chosenClassName)) {
        clickedElem.classList.remove(chosenClassName)
        if (clickedElem.tagName === 'P') {
            (clickedElem.parentNode as HTMLElement).style.cursor = 'default'
        }
    } else {
        clickedElem.classList.add(chosenClassName)
        if (clickedElem.tagName === 'P') {
            (clickedElem.parentNode as HTMLElement).style.cursor = 'move'
        }
    }

    let selectedElems = new Array<string>()
    const allSelectedElems = document.getElementsByClassName(chosenClassName)
    for (let i = 0; i < allSelectedElems.length; i++) {
        if (!allSelectedElems[i].classList.contains(chosenClassName)) continue

        const selectedElemId = allSelectedElems[i].id
        if (selectedElemId) {
            selectedElems.push(selectedElemId)
        }
    }
    dispatch({type: CHOOSE_ELEMENTS, payload: selectedElems})
}

const singleElemSelection = (clickedElem: any, chosenClassName: string, dispatch: (value: any) => void, id: string) => {
    if (clickedElem.classList.contains(chosenClassName)) return

    let selectedElements = document.getElementsByClassName(chosenClassName)
    for (let i = 0; i < selectedElements.length; i++) {
        if (selectedElements[i].classList.contains(chosenClassName)) {
            selectedElements[i].classList.remove(chosenClassName)
        }
    }

    clickedElem.classList.toggle(chosenClassName)
    if (clickedElem.tagName === 'P') {
        (clickedElem.parentNode as HTMLElement).style.cursor = 'move'
    }
    dispatch({type: CHOOSE_ELEMENTS, payload: [id]})
}
