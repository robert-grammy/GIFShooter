package ru.robert_grammy.gifshooter.ui.component.input

import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.Config
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.ThemeComponent
import ru.robert_grammy.gifshooter.utils.FileManager
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.io.File
import javax.swing.BorderFactory
import javax.swing.JTextField

class OutputPathField : JTextField(), ThemeComponent, FocusListener {

    init {
        ComponentDimension.TEXT_FIELD.setExactly(this)
        text = Config.OUTPUT_FOLDER.get()
        addFocusListener(this)
        addActionListener {
            focusLost(null)
        }
    }

    fun getFolder(): File {
        return File(FileManager.changeKeysToPaths(text))
    }

    override fun updateTheme() {
        border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        )
        caretColor = Theme.TEXT_COLOR.get()
    }

    override fun focusGained(e: FocusEvent?) {}

    override fun focusLost(e: FocusEvent?) {
        if (text.isNotEmpty()) Config.OUTPUT_FOLDER.set(text)
    }

}