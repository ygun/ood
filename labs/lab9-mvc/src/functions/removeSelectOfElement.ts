import {changeTextStyleMenu} from "./changeTextStyleMenu"
import {Dispatch} from "react"
import {CHOOSE_ELEMENTS} from "../store/actionTypes"
import {changePrimitiveStyleMenu} from "./changePrimitiveStyleMenu"


export const pathClassName = 'elem-path_active'

export function removeSelectOfElement(evt: any, dispatch: Dispatch<any>) {
    let clickedElem = evt.target as HTMLElement
    let navBar = document.getElementById('nav_bar')
    let itsNavBar = null
    let itsClickedElem = false

    if (navBar) {
        itsNavBar = evt.target === navBar || (navBar.contains(evt.target as Node) && true)
    }

    if (clickedElem.getAttribute('data-is-element') || clickedElem.getAttribute('data-value') || clickedElem.tagName === 'foreignObject') {
        itsClickedElem = true
    }

    if ((!itsNavBar && !itsClickedElem)) {
        removeSelectionBorder()
        changePrimitiveStyleMenu(false)
        changeTextStyleMenu(false)
        dispatch({type: CHOOSE_ELEMENTS, payload: new Array<string>()})
    }
}


export function removeSelectionBorder() {
    let multipleSelection = document.getElementById('selection-border') as HTMLElement
    if (multipleSelection) {
        let className = 'element_choosed'
        let allSelectedElems = document.getElementsByClassName(className)
        while (allSelectedElems[0]) {
            if (allSelectedElems[0].classList.contains(className)) {
                if (allSelectedElems[0].tagName === 'P') {
                    (allSelectedElems[0].parentNode as HTMLElement).style.cursor = 'default'
                }

                allSelectedElems[0].classList.remove(className)
            }
        }
    }
}