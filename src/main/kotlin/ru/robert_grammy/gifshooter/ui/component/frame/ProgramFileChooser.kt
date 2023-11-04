package ru.robert_grammy.gifshooter.ui.component.frame

import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.ThemeComponent
import javax.swing.*

object ProgramFileChooser : JFileChooser(), ThemeComponent, LocaleComponent {

    private fun readResolve(): Any = ProgramFileChooser

    private val openButton: JButton
    private val closeButton: JButton

    init {
        fileSelectionMode = DIRECTORIES_ONLY
        val buttons = ((components[3] as JPanel).components[3] as JPanel).components

        openButton = buttons[0] as JButton
        ComponentDimension.FILE_CHOOSER_BUTTON.setExactly(openButton)

        closeButton = buttons[1] as JButton
        ComponentDimension.FILE_CHOOSER_BUTTON.setExactly(closeButton)
    }

    fun open(): Int {
        updateTexts()
        return showOpenDialog(null)
    }

    override fun updateTheme() {
        SwingUtilities.updateComponentTreeUI(this)
        //TODO Buttons background fix

        openButton.background = Theme.PRIMARY_COLOR.get()
        openButton.border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get())

        closeButton.background = Theme.PRIMARY_COLOR.get()
        closeButton.border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get())
    }

    override fun updateTexts() {
        openButton.text = Strings.FILE_CHOOSER_BUTTON_OK.get()
        closeButton.text = Strings.FILE_CHOOSER_BUTTON_CANCEL.get()
        updateTheme()
    }

}