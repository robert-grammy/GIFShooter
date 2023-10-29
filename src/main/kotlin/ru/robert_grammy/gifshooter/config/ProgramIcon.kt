package ru.robert_grammy.gifshooter.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import javax.swing.ImageIcon

enum class ProgramIcon(private val filename: String) {

    PROGRAM("program_icon.png"),
    CLOSE("close_program_button_icon.png"),
    MINIMIZE("minimize_program_button_icon.png"),
    RESET("reset_button_icon.png"),
    FOLDER("select_folder_button_icon.png"),
    DROPDOWN_DEFAULT("dropdown_button_icon.png"),
    DROPDOWN_ACTIVE("dropdown_active_button_icon.png"),
    RECORDING_STATUS_OFF("recording_status_inactive_icon.png"),
    RECORDING_STATUS_ON("recording_status_active_icon.png"),
    THEME("select_theme_button_icon.png"),
    PLANET("select_locale_button_icon.png");

    private val icon: ImageIcon = ResourceLoader.getIcon(filename)

    fun get() = icon

}