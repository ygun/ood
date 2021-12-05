import {Dispatch} from "react"
import {CHOOSE_ELEMENTS} from "../store/actionTypes"
import {changePrimitiveStyleMenu} from "./changePrimitiveStyleMenu"


export const removeSelectOfElements = (evt: any, dispatch: Dispatch<any>) => {
    const clickedElem = evt.target as HTMLElement
    const navBar = document.getElementById('nav_bar')
    let isNavBarClicked = null
    let isElemClicked = false

    if (navBar) {
        isNavBarClicked = evt.target === navBar || (navBar.contains(evt.target as Node) && true)
    }

    if (clickedElem.getAttribute('data-is-element')
        || clickedElem.getAttribute('data-value')
        || clickedElem.tagName === 'foreignObject'
    ) {
        isElemClicked = true
    }

    if ((!isNavBarClicked && !isElemClicked)) {
        removeSelectionBorder()
        changePrimitiveStyleMenu(false)
        dispatch({type: CHOOSE_ELEMENTS, payload: new Array<string>()})
    }
}


export function removeSelectionBorder() {
    const selectionBorder = document.getElementById('selection-border') as HTMLElement
    if (!selectionBorder) return

    const className = 'element_chosen'
    let allSelectedElems = document.getElementsByClassName(className)
    for (let i = 0; i < allSelectedElems.length; i++) {
        if (!allSelectedElems[i].classList.contains(className)) continue

        if (allSelectedElems[i].tagName === 'P') {
            (allSelectedElems[i].parentNode as HTMLElement).style.cursor = 'default'
        }
        allSelectedElems[i].classList.remove(className)
    }
}