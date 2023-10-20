package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader

enum class Config {

    THEME,
    LOCALE;

    companion object {
        private val CONFIG = ResourceLoader.getProperties("config.properties")

        init {
            entries.forEach {
                it.value = CONFIG.getProperty(it.name.lowercase())
            }
        }
    }

    lateinit var value: String
        private set

}