package ru.robert_grammy.gifshooter.utils

import java.awt.Font
import java.awt.Image
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.URL
import java.util.*
import javax.imageio.ImageIO
import javax.swing.ImageIcon

object ResourceLoader {

    const val ASSETS_DIR = "assets/"
    const val FONTS_DIR = "${ASSETS_DIR}fonts/"
    const val IMAGES_DIR = "${ASSETS_DIR}images/"
    const val ICONS_DIR = "${IMAGES_DIR}icons/"

    const val CONFIGS_DIR = "configs/"
    const val LOCALES_DIR = "${CONFIGS_DIR}locales/"
    const val THEMES_DIR = "${CONFIGS_DIR}themes/"

    val CLASS_LOADER: ClassLoader = Thread.currentThread().contextClassLoader

    fun getResource(path: String): URL? = CLASS_LOADER.getResource(path)
    fun getResourceAsStream(path: String): InputStream? = CLASS_LOADER.getResourceAsStream(path)

    fun resourceExists(name: String, dirPath: String) : Boolean {
        return Optional.ofNullable(getResource(dirPath.plus(name))).isPresent
    }

    fun getIcon(name: String, dirPath: String = ICONS_DIR) = ImageIcon(getResource(dirPath.plus(name)))

    fun getFont(name: String, dirPath: String = FONTS_DIR) : Font {
        val inputStream = getResourceAsStream(dirPath.plus(name))?.let {
            BufferedInputStream(it)
        }
        return Font.createFont(Font.TRUETYPE_FONT, inputStream)
    }

    fun getImage(name: String, dirPath: String = IMAGES_DIR) : Image {
        val inputStream = getResourceAsStream(dirPath.plus(name))?.let {
            BufferedInputStream(it)
        }
        return ImageIO.read(inputStream)
    }

    fun getProperties(name: String, dirPath: String = CONFIGS_DIR): Properties {
        return Properties().apply {
            this.load(
                getResourceAsStream(dirPath.plus(name))?.let {
                    BufferedInputStream(it)
                }
            )
        }
    }

    fun getLocaleProperties(name: String) = getProperties(name, LOCALES_DIR)

    fun getThemeProperties(name: String) = getProperties(name, THEMES_DIR)

}