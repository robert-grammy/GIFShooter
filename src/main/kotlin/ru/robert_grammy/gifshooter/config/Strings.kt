package ru.robert_grammy.gifshooter.config

import java.util.Locale
import java.util.ResourceBundle

enum class Strings(private val key: String) {

    PROGRAM_NAME("gui_shooter.program.name");

    companion object {
        private const val BUNDLE_NAME = "strings"
        private const val LOCALE_LANG_AND_STATE_SEPARATOR = "_"

        private lateinit var CONFIG : ResourceBundle

        fun setLocale(locale: String) {
            CONFIG = if (locale.contains(LOCALE_LANG_AND_STATE_SEPARATOR)) {
                val localeParts = locale.split(LOCALE_LANG_AND_STATE_SEPARATOR)
                ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(localeParts[0], localeParts[1]))
            } else {
                ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(locale))
            }
        }

        fun load() {
            entries.forEach {
                it.value = CONFIG.getString(it.key)
            }
        }

        init {
            setLocale(Config.LOCALE.value)
            load()
        }
    }

    lateinit var value: String
        private set

}