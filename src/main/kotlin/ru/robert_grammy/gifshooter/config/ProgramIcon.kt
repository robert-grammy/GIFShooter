package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.image.BufferedImage
import java.awt.image.DataBufferInt
import javax.swing.ImageIcon

enum class ProgramIcon(filename: String) {

    PROGRAM("program_icon.png"),
    CLOSE("close_program_button_icon.png"),
    MINIMIZE("minimize_program_button_icon.png"),
    RESET("reset_button_icon.png"),
    FOLDER("select_folder_button_icon.png"),
    DROPDOWN_DEFAULT("dropdown_button_icon.png"),
    DROPDOWN_ACTIVE("dropdown_active_button_icon.png"),
    RECORDING_STATUS("recording_status_icon.png"),
    THEME("select_theme_button_icon.png"),
    PLANET("select_locale_button_icon.png");

    companion object {
        private const val MAX_BYTE_VALUE: Double = 255.0
        private val COLORED_ICONS = HashMap<String, HashMap<Int, ImageIcon>>()
    }

    private val icon = ResourceLoader.getIcon(filename)

    fun get() = icon

    fun getColored(hexColor: Int) : ImageIcon {
        if (COLORED_ICONS.containsKey(name)) {
            if (COLORED_ICONS[name]!!.containsKey(hexColor)) {
                return COLORED_ICONS[name]!![hexColor]!!
            }
        } else COLORED_ICONS[name] = HashMap()

        val image = BufferedImage(icon.iconWidth, icon.iconHeight, BufferedImage.TYPE_INT_ARGB)
        val graphics = image.createGraphics()
        icon.paintIcon(null, graphics, 0, 0)
        graphics.dispose()

        val pixels = (image.raster.dataBuffer as DataBufferInt).data

        for (i in 0 ..< image.width * image.height) {
            val currentPixel = pixels[i]
            val alpha = getAlpha(currentPixel)
            val red = (getRed(hexColor) * (getRed(currentPixel) / MAX_BYTE_VALUE)).toInt()
            val green = (getGreen(hexColor) * (getGreen(currentPixel) / MAX_BYTE_VALUE)).toInt()
            val blue = (getBlue(hexColor) * (getBlue(currentPixel) / MAX_BYTE_VALUE)).toInt()
            pixels[i] = getHex(alpha, red, green, blue)
        }

        COLORED_ICONS[name]!![hexColor] = ImageIcon(image)
        return COLORED_ICONS[name]!![hexColor]!!
    }

    private fun getAlpha(hex: Int) : Int {
        return hex.rotateRight(24).and(0xFF)
    }

    private fun getRed(hex: Int) : Double {
        return hex.rotateRight(16).and(0xFF).toDouble()
    }

    private fun getGreen(hex: Int) : Double {
        return hex.rotateRight(8).and(0xFF).toDouble()
}

    private fun getBlue(hex: Int) : Double {
        return hex.and(0xFF).toDouble()
    }

    private fun getHex(alpha: Int, red: Int, green: Int, blue: Int) : Int {
        return blue + green.rotateLeft(8) + red.rotateLeft(16) + alpha.rotateLeft(24)
    }

}