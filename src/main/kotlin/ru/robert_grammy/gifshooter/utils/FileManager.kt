package ru.robert_grammy.gifshooter.utils

import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import javax.swing.filechooser.FileSystemView

object FileManager {

    const val CONFIGS_DIR = "configs/"
    const val THEMES_DIR = "${CONFIGS_DIR}themes/"
    const val LOCALES_DIR = "${CONFIGS_DIR}locales/"

    private const val EXTENSION = ".properties"

    const val CONFIG_FILENAME = "config$EXTENSION"
    private const val THEME_FILENAME_FORMAT = "theme_%s$EXTENSION"
    private const val LOCALE_FILENAME_FORMAT = "strings_%s$EXTENSION"

    val LOCAL_DIR = PathKeys.PROGRAM_DIR.value.plus(File.separator)

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

    fun changeKeysToPaths(pathWithKeys: String) : String {
        var path = pathWithKeys
        PathKeys.entries.forEach {
            path = it.replace(path)
        }
        return path
    }

    fun loadFiles() {
        loadConfigFile()
        loadThemeFiles()
        loadLocaleFiles()
    }

    fun getThemesFolder() : File {
        return File(LOCAL_DIR, THEMES_DIR)
    }

    fun getLocalesFolder() : File {
        return File(LOCAL_DIR, LOCALES_DIR)
    }

    private fun loadConfigFile() {
        if (generalConfigExists()) return
        val inputStream = ResourceLoader.getResourceAsStream(ResourceLoader.CONFIGS_DIR.plus(CONFIG_FILENAME))!!
        copyFile(inputStream, CONFIGS_DIR, CONFIG_FILENAME)
    }

    private fun loadThemeFiles() {
        DEFAULT_THEMES.forEach {
            if (!defaultThemeExists(it)) {
                val filename = THEME_FILENAME_FORMAT.format(it)
                val inputStream = ResourceLoader.getResourceAsStream(ResourceLoader.THEMES_DIR.plus(filename))!!
                copyFile(inputStream, THEMES_DIR, filename)
            }
        }
    }

    private fun loadLocaleFiles() {
        DEFAULT_LOCALES.forEach {
            if (defaultLocaleExists(it)) return
            val filename = LOCALE_FILENAME_FORMAT.format(it)
            val inputStream = ResourceLoader.getResourceAsStream(ResourceLoader.LOCALES_DIR.plus(filename))!!
            copyFile(inputStream, LOCALES_DIR, filename)
        }
    }

    private fun generalConfigExists() : Boolean {
        return File("$LOCAL_DIR$CONFIGS_DIR$CONFIG_FILENAME").exists()
    }

    private fun defaultThemeExists(theme: String) : Boolean {
        return File("$LOCAL_DIR$THEMES_DIR${THEME_FILENAME_FORMAT.format(theme)}").exists()
    }

    private fun defaultLocaleExists(locale: String) : Boolean {
        return File("$LOCAL_DIR$LOCALES_DIR${LOCALE_FILENAME_FORMAT.format(locale)}").exists()
    }

    private fun copyFile(input: InputStream, targetFolder: String, filename: String) {
        val folder = File("$LOCAL_DIR$targetFolder")
        val file = File("$LOCAL_DIR$targetFolder$filename")
        folder.mkdirs()
        file.createNewFile()
        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
        input.close()
    }

    enum class PathKeys(private val key: String, val value: String) {

        DOCUMENTS("DOCUMENTS", FileSystemView.getFileSystemView().defaultDirectory.path),
        DESKTOP("DESKTOP", System.getProperty("user.home").plus(File.separator).plus("Desktop")),
        PROGRAM_DIR("PROGRAM_DIR", File(ResourceLoader.javaClass.protectionDomain.codeSource.location.path).parent);

        fun replace(path: String) = path.replace("%$key%", value)

    }

}