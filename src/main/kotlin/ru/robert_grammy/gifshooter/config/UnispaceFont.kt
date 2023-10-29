package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Font
import java.awt.GraphicsEnvironment
import javax.swing.plaf.FontUIResource

enum class UnispaceFont(private val size: Int) {

    SIZE_16(16),
    SIZE_12(12);

    companion object {
        private const val FONT_FILENAME = "unispace.ttf"
        private val FONT_NAME: String
        private val FONT_MAP = HashMap<Int, Font>()

        init {
            val font = ResourceLoader.getFont(FONT_FILENAME)
            FONT_NAME = font.fontName
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font)
        }
    }

    fun get() : Font {
        if (!FONT_MAP.containsKey(size)) {
            FONT_MAP[size] = Font(FONT_NAME, Font.TRUETYPE_FONT, size)
        }
        return FONT_MAP[size]!!
    }

    fun resource() = FontUIResource(get())

}