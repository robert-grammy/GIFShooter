package ru.robert_grammy.gifshooter.ui.config

import ru.robert_grammy.gifshooter.config.Config
import ru.robert_grammy.gifshooter.config.UIProperties
import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Color
import java.util.Locale
import java.util.ResourceBundle
import javax.swing.plaf.ColorUIResource

enum class Theme {

    PRIMARY_COLOR,
    SECONDARY_COLOR,
    HOVER_COLOR,
    ACTIVE_COLOR,
    TEXT_COLOR,
    BORDER_COLOR,
    HOVER_CLOSE_BUTTON,
    ACTIVE_CLOSE_BUTTON;

    companion object {
        private const val BUNDLE_NAME = "${ResourceLoader.THEMES_DIR}theme"
        private lateinit var CONFIG: ResourceBundle

        private fun setSettings(themeName: String) {
            CONFIG = ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(themeName))
        }

        private fun load() {
            entries.forEach {
                it.value = Color(Integer.decode(CONFIG.getString(it.name.lowercase())))
            }
        }

        fun reload(themeName: String) {
            setSettings(themeName)
            load()
            UIProperties.loadProperties()
        }

        init {
            reload(Config.THEME.get())
        }
    }

    private var value: Color = Color(0xFF.rotateLeft(24), true)

    fun get() = value
    fun resource() = ColorUIResource(value)

}