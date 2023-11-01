package ru.robert_grammy.gifshooter.ui.component.frame

import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.ProgramFont
import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import java.awt.Insets
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JPanel

object ProgramFileChooser : JFileChooser() {
    private fun readResolve(): Any = ProgramFileChooser

    init {
        fileSelectionMode = DIRECTORIES_ONLY
        val buttons = ((components[3] as JPanel).components[3] as JPanel).components

        val openButton = buttons[0] as JButton
        openButton.background = Theme.PRIMARY_COLOR.get()
        openButton.border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get())
        openButton.text = Strings.FILE_CHOOSER_BUTTON_OK.get()
        ComponentDimension.FILE_CHOOSER_BUTTON.setExactly(openButton)

        val closeButton = buttons[1] as JButton
        closeButton.background = Theme.PRIMARY_COLOR.get()
        closeButton.border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get())
        closeButton.text = Strings.FILE_CHOOSER_BUTTON_CANCEL.get()
        ComponentDimension.FILE_CHOOSER_BUTTON.setExactly(closeButton)
    }

    fun open(): Int {
        return showOpenDialog(null)
    }

}