package ru.robert_grammy.gifshooter.ui.component.frame

import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.ThemeComponent
import javax.swing.*

object ProgramFileChooser : JFileChooser(), ThemeComponent, LocaleComponent {

    private fun readResolve(): Any = ProgramFileChooser

    private lateinit var openButton: JButton
    private lateinit var closeButton: JButton

    init {
        fileSelectionMode = DIRECTORIES_ONLY
        updateButtons()
    }

    fun open(): Int {
        updateTexts()
        return showOpenDialog(null)
    }

    override fun updateTheme() {
        SwingUtilities.updateComponentTreeUI(this)
        updateButtons()
    }

    private fun updateButtons() {
        val buttons = ((components[3] as JPanel).components[3] as JPanel).components

        openButton = buttons[0] as JButton
        ComponentDimension.FILE_CHOOSER_BUTTON.setExactly(openButton)
        openButton.border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
        openButton.background = Theme.PRIMARY_COLOR.get()

        closeButton = buttons[1] as JButton
        ComponentDimension.FILE_CHOOSER_BUTTON.setExactly(closeButton)
        closeButton.border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
        closeButton.background = Theme.PRIMARY_COLOR.get()
    }

    override fun updateTexts() {
        openButton.text = Strings.FILE_CHOOSER_BUTTON_OK.get()
        closeButton.text = Strings.FILE_CHOOSER_BUTTON_CANCEL.get()
        updateTheme()
    }

}