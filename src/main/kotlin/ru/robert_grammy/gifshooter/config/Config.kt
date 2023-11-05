package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.FileManager
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*

enum class Config {

    OUTPUT_FOLDER,
    THEME,
    LOCALE;

    companion object {
        private val configPath = "${FileManager.LOCAL_DIR}${FileManager.CONFIGS_DIR}${FileManager.CONFIG_FILENAME}"
        private val CONFIG = Properties().apply { load(FileReader(configPath)) }

        init {
            entries.forEach {
                it.value = CONFIG.getProperty(it.name.lowercase())
            }
        }
    }

    private lateinit var value: String

    fun get() = value

    fun set(value: String) {
        CONFIG.setProperty(name.lowercase(), value)
        save()
    }

    private fun save() {
        val writer = BufferedWriter(FileWriter(configPath))
        for (key in CONFIG.propertyNames()) {
            writer.write("$key=${CONFIG[key]}")
            writer.newLine()
        }
        writer.close()
    }

}