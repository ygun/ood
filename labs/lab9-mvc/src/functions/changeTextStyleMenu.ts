function changeVisibility(elementId: string, visibility: boolean) {
    let element = document.getElementById(elementId)
    if (!visibility) {
        let fonSize = document.getElementById('font-size-area') as HTMLElement
        if (fonSize) {
            fonSize.blur()
        }
    }

    if (element) {
        element.style.visibility = visibility ? 'visible' : 'hidden'
        element.style.display = visibility ? 'block' : ''
    }
}

export function changeTextStyleMenu(visibility: boolean) {
    changeVisibility('edit_style_text_sep_0', visibility)
    changeVisibility('edit_style_text_delete', visibility)
    changeVisibility('edit_style_text_sep_1', visibility)
    changeVisibility('edit_style_text_size', visibility)
    changeVisibility('edit_style_text_color', visibility)
}