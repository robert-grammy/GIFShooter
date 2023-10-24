package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.util.Locale
import java.util.ResourceBundle

enum class Strings(private val key: String) {

    PROGRAM_NAME("gui_shooter.program.name"),
    AREA_LINE_LABEL("gui_shooter.content_pane.area_selector_pane.label"),
    AREA_SELECTOR_SCREEN_TYPE("gui_shooter.content_pane.area_selector_pane.type_selector.screen"),
    AREA_SELECTOR_AREA_TYPE("gui_shooter.content_pane.area_selector_pane.type_selector.area"),
    ALLOCATION_BUTTON("gui_shooter.content_pane.area_selector_pane.allocation_button.text"),
    SCREEN_SELECTOR_ALL("gui_shooter.content_pane.area_selector_pane.screen_selector.all"),
    SCREEN_SELECTOR_SCREEN_FORMAT("gui_shooter.content_pane.area_selector_pane.screen_selector.screen_nth"),
    OUTPUT_LINE_LABEL("gui_shooter.content_pane.output_pane.label");

    companion object {
        private const val BUNDLE_NAME = "configs.locales.strings"
        private const val LOCALE_LANG_AND_STATE_SEPARATOR = "_"

        private lateinit var CONFIG : ResourceBundle

        private fun setLocale(locale: String) {
            CONFIG = if (locale.contains(LOCALE_LANG_AND_STATE_SEPARATOR)) {
                val localeParts = locale.split(LOCALE_LANG_AND_STATE_SEPARATOR)
                ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(localeParts[0], localeParts[1]), ResourceLoader.CLASS_LOADER)
            } else {
                ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(locale), ResourceLoader.CLASS_LOADER)
            }
        }

        private fun load() {
            entries.forEach {
                it.value = CONFIG.getString(it.key)
            }
        }

        fun reload(locale: String) {
            setLocale(locale)
            load()
        }

        init {
            reload(Config.LOCALE.get())
        }
    }

    private lateinit var value: String

    fun get() = value

}