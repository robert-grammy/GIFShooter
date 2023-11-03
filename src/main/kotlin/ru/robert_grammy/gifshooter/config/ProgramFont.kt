package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Font
import java.awt.GraphicsEnvironment
import javax.swing.plaf.FontUIResource

enum class ProgramFont(private val size: Int) {

    SIZE_16(16),
    SIZE_12(12);

    companion object {
        private const val DEFAULT_FILENAME = "unispace.ttf"
        private lateinit var FONT_NAME: String
        private var FONT_MAP = HashMap<String, HashMap<Int, Font>>()

        fun reload() {
            val fontFilename = Strings.FONT_FILENAME.get()
            val font = if (ResourceLoader.resourceExists(fontFilename, ResourceLoader.FONTS_DIR)) {
                ResourceLoader.getFont(fontFilename)
            } else {
                ResourceLoader.getFont(DEFAULT_FILENAME)
            }
            FONT_NAME = font.fontName
            if (!FONT_MAP.containsKey(FONT_NAME)) {
                FONT_MAP[FONT_NAME] = HashMap()
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font)
            }
            UIProperties.loadFont()
        }

        init {
            reload()
        }
    }

    fun get() : Font {
        val currentMap = FONT_MAP[FONT_NAME]!!
        if (!currentMap.containsKey(size)) {
            currentMap[size] = Font(FONT_NAME, Font.TRUETYPE_FONT, size)
        }
        return currentMap[size]!!
    }

    fun resource() = FontUIResource(get())

}