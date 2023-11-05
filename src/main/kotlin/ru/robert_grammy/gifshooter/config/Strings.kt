package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.FileManager
import java.io.File
import java.net.URLClassLoader
import java.util.*

enum class Strings(private val key: String) {

    PROGRAM_NAME("gui_shooter.name"),
    AREA_LINE_LABEL("gui_shooter.main_frame.area_selector_pane.label"),
    AREA_SELECTOR_SCREEN_TYPE("gui_shooter.main_frame.area_selector_pane.type_selector.screen"),
    AREA_SELECTOR_AREA_TYPE("gui_shooter.main_frame.area_selector_pane.type_selector.area"),
    ALLOCATION_BUTTON("gui_shooter.main_frame.area_selector_pane.allocation_button.text"),
    SCREEN_SELECTOR_ALL("gui_shooter.main_frame.area_selector_pane.screen_selector.all"),
    SCREEN_SELECTOR_SCREEN_FORMAT("gui_shooter.main_frame.area_selector_pane.screen_selector.screen_nth"),
    OUTPUT_LINE_LABEL("gui_shooter.main_frame.output_pane.label"),
    FPS_LINE_LABEL("gui_shooter.main_frame.fps_selector_pane.label"),
    FPS_SELECTOR_FORMAT("gui_shooter.main_frame.fps_selector_pane.fps_format"),
    DELAY_LINE_LABEL("gui_shooter.main_frame.delay_selector_pane.label"),
    DELAY_SELECTOR_FORMAT("gui_shooter.main_frame.delay_selector_pane.delay_format"),
    START_CAPTURE_BUTTON("gui_shooter.main_frame.capture_and_settings_pane.capture_button.start"),
    STOP_CAPTURE_BUTTON("gui_shooter.main_frame.capture_and_settings_pane.capture_button.stop"),
    PROGRESS_BAR_RECORD_IN_PROCESS_FORMAT("gui_shooter.main_frame.results_scroll_pane.progress_bar.is_recording"),
    PROGRESS_BAR_RECORD_SAVING_FORMAT("gui_shooter.main_frame.results_scroll_pane.progress_bar.is_saving"),
    PROGRESS_BAR_RECORD_COMPLETE_FORMAT("gui_shooter.main_frame.results_scroll_pane.progress_bar.done"),

    FREE_AREA_TITLE("gui_shooter.free_area.title_bar.title"),
    FREE_AREA_SIZE_INFO_FORMAT("gui_shooter.free_area.bottom_bar.label"),

    DIALOG_SELECT_LOCALE_TITLE("gui_shooter.dialog.locale_select.title"),
    DIALOG_SELECT_LOCALE_BUTTON_OK("gui_shooter.dialog.locale_select.button.confirm"),
    DIALOG_SELECT_LOCALE_BUTTON_CANCEL("gui_shooter.dialog.locale_select.button.cancel"),
    DIALOG_SELECT_THEME_TITLE("gui_shooter.dialog.theme_select.title"),
    DIALOG_SELECT_THEME_BUTTON_OK("gui_shooter.dialog.theme_select.button.confirm"),
    DIALOG_SELECT_THEME_BUTTON_CANCEL("gui_shooter.dialog.theme_select.button.cancel"),

    FILE_CHOOSER_BUTTON_OK("swing.file_chooser.button.ok"),
    FILE_CHOOSER_BUTTON_CANCEL("swing.file_chooser.button.cancel"),

    FONT_FILENAME("font_filename");

    companion object {
        private const val BUNDLE_NAME = "strings"
        private val BUNDLE_PATH = "${FileManager.LOCAL_DIR}${FileManager.LOCALES_DIR}"
        private const val BUNDLE_EXTENSION = ".properties"
        private const val LOCALE_LANG_AND_STATE_SEPARATOR = "_"
        private val DEFAULT_LOCALE = Locale.of("en_US")

        const val LOCALE_DISPLAY_NAME_FORMAT = "%s (%s)"

        private lateinit var CONFIG : ResourceBundle
        private val LOCALES = LinkedHashMap<String, Locale>()

        fun getLocales() = LinkedHashMap(LOCALES)

        private fun setLocale(localeFormat: String) {
            val folder = File(BUNDLE_PATH)
            val loader = URLClassLoader(arrayOf(folder.toURI().toURL()))

            if (LOCALES.containsKey(localeFormat)) {
                CONFIG = ResourceBundle.getBundle(BUNDLE_NAME, LOCALES.getOrDefault(localeFormat, DEFAULT_LOCALE), loader)
                return
            }

            CONFIG = if (localeFormat.contains(LOCALE_LANG_AND_STATE_SEPARATOR)) {
                val localeParts = localeFormat.split(LOCALE_LANG_AND_STATE_SEPARATOR)
                ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(localeParts[0], localeParts[1]), loader)
            } else {
                ResourceBundle.getBundle(BUNDLE_NAME, Locale.of(localeFormat), loader)
            }
        }

        private fun load() {
            entries.forEach {
                it.value = CONFIG.getString(it.key)
            }
        }
        private fun loadLocales() {
            val folder = FileManager.getLocalesFolder()
            folder.listFiles()!!.map(File::getName).filter {
                    it.startsWith(BUNDLE_NAME) && it.endsWith(BUNDLE_EXTENSION)
                }.map {
                    it.substring(BUNDLE_NAME.length+1, it.indexOf(BUNDLE_EXTENSION))
                }.forEach {
                    LOCALES[it] = if (it.contains(LOCALE_LANG_AND_STATE_SEPARATOR)) {
                        val localeParts = it.split(LOCALE_LANG_AND_STATE_SEPARATOR)
                        Locale.of(localeParts[0], localeParts[1])
                    } else {
                        Locale.of(it)
                    }
            }
        }

        fun reload(locale: String) {
            setLocale(locale)
            load()
        }

        init {
            loadLocales()
            reload(Config.LOCALE.get())
        }
    }

    private lateinit var value: String

    fun get() = value

}