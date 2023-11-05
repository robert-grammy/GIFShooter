package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.FileManager
import java.awt.Color
import java.io.File
import java.net.URLClassLoader
import java.util.*
import javax.swing.plaf.ColorUIResource

enum class Theme {

    PRIMARY_COLOR,
    SECONDARY_COLOR,
    HOVER_COLOR,
    ACTIVE_COLOR,
    TEXT_COLOR,
    BORDER_COLOR,
    HOVER_CLOSE_BUTTON,
    ACTIVE_CLOSE_BUTTON,
    CAPTURE,
    DIALOG_BORDER;

    companion object {
        private const val BUNDLE_NAME = "theme"
        private const val BUNDLE_EXTENSION = ".properties"
        private const val DEFAULT_THEME = "DefaultBlue"
        private val BUNDLE_PATH = "${FileManager.LOCAL_DIR}${FileManager.THEMES_DIR}"
        private lateinit var CONFIG: ResourceBundle

        private fun setSettings(themeName: String) {
            val file = File(BUNDLE_PATH)
            val loader = URLClassLoader(arrayOf(file.toURI().toURL()))
            CONFIG = ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(themeName), loader)
        }

        private fun load() {
            entries.forEach {
                it.value = Color(Integer.decode(CONFIG.getString(it.name.lowercase())))
            }
        }

        private val themes = ArrayList<String>()

        fun getThemeNames() = ArrayList(themes)

        private fun loadThemes() {
            val themeFolder = FileManager.getThemesFolder()
            themeFolder.listFiles()!!.map(File::getName).filter {
                it.startsWith(BUNDLE_NAME) && it.endsWith(BUNDLE_EXTENSION)
            }.map {
                it.substring(BUNDLE_NAME.length+1, it.indexOf(BUNDLE_EXTENSION))
            }.forEach {
                themes.add(it)
            }
        }

        val TRANSPARENT_COLOR = Color(0x00000000, true)
        val BACKGROUND_DARKER = Color(0xAA555555.toInt(), true)

        fun reload(themeName: String) {
            if (themes.contains(themeName)) {
                setSettings(themeName)
            } else {
                setSettings(DEFAULT_THEME)
            }
            load()
            UIProperties.loadProperties()
        }

        init {
            loadThemes()
            reload(Config.THEME.get())
        }
    }

    private var value: Color = Color(0x00000000, true)

    fun get() = value

    fun resource() = ColorUIResource(value)

    fun hex() = value.rgb

}