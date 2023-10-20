package ru.robert_grammy.gifshooter.ui.model

import ru.robert_grammy.gifshooter.config.Config
import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Color
import java.util.Locale
import java.util.ResourceBundle

enum class Theme {

    PRIMARY_COLOR,
    SECONDARY_COLOR,
    HOVER_COLOR,
    ACTIVE_COLOR,
    TEXT_COLOR,
    BORDER_COLOR;

    companion object {
        private val TRANSPARENT = Color(0xFF.rotateLeft(24), true)
        private const val BUNDLE_NAME = "${ResourceLoader.THEMES_DIR}theme"
        private lateinit var CONFIG: ResourceBundle

        fun setSettings(themeName: String) {
            CONFIG = ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(themeName))
        }

        fun load() {
            entries.forEach {
                it.value = Color(Integer.decode(CONFIG.getString(it.name.lowercase())))
            }
        }

        init {
            setSettings(Config.THEME.value)
            load()
        }
    }

    lateinit var value: Color
        private set

}