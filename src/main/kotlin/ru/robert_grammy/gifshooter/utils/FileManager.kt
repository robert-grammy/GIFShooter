package ru.robert_grammy.gifshooter.utils

import java.io.File

object FileManager {

    private const val CONFIGS_DIR = "config/"
    private const val THEMES_DIR = "${CONFIGS_DIR}theme/"
    private const val LOCALES_DIR = "${CONFIGS_DIR}locales/"

    private const val EXTENSION = ".properties"

    private const val CONFIG_FILENAME = "config$EXTENSION"
    private const val THEME_FILENAME_FORMAT = "theme_%d$EXTENSION"
    private const val LOCALE_FILENAME_FORMAT = "strings_%d$EXTENSION"

    private val DEFAULT_THEMES = arrayOf(
        "DefaultRed",
        "DefaultOrange",
        "DefaultYellow",
        "DefaultGreen",
        "DefaultLightblue",
        "DefaultBlue",
        "DefaultPurple",
        "Grayscale",
        "CyberpunkAcid"
    )

    private val DEFAULT_LOCALES = arrayOf(
        "en_US",
        "ru_RU",
        "de_DE"
    )

    private fun generalConfigExists() : Boolean {
        return File(CONFIG_FILENAME).exists()
    }

    private fun defaultThemeExists(theme: String) : Boolean {
        return File(THEME_FILENAME_FORMAT.format(theme)).exists()
    }

    private fun defaultLocaleExists(locale: String) : Boolean {
        return File(LOCALE_FILENAME_FORMAT.format(locale)).exists()
    }

    //TODO Create funcs to load configs to local dir

}