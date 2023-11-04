package ru.robert_grammy.gifshooter.ui.component.input

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.ThemeComponent
import java.io.File
import javax.swing.BorderFactory
import javax.swing.JTextField
import javax.swing.filechooser.FileSystemView

class OutputPathField : JTextField(), ThemeComponent {

    init {
        text = FileSystemView.getFileSystemView().defaultDirectory.path.plus("\\").plus(Strings.PROGRAM_NAME.get())
    }

    fun getFolder(): File {
        return File(text)
    }

    override fun updateTheme() {
        border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        )
        caretColor = Theme.TEXT_COLOR.get()
    }

}