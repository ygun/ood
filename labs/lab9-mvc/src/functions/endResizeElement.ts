export const endResizeElement = () => {
    const multipleSelection = document.getElementById('selection-border') as HTMLElement
    let selectionBorder = multipleSelection.children[0]
    selectionBorder.setAttribute('d', multipleSelection.getAttribute('data-old-d') as string)
}