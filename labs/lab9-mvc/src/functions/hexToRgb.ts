import {Color} from "../entities/Color"

const HEX_TO_RGB_REGEX = /^#?([a-f\d])([a-f\d])([a-f\d])$/i

export const hexToRgb = (hex: string) => {
    hex = hex.replace(HEX_TO_RGB_REGEX, (m, r, g, b) => r + r + g + g + b + b)

    const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex)
    return result ? {
        red: parseInt(result[1], 16),
        green: parseInt(result[2], 16),
        blue: parseInt(result[3], 16)
    } as Color : {
        red: 255,
        green: 255,
        blue: 255
    }
}