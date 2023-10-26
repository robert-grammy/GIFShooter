package ru.robert_grammy.gifshooter.ui.component

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.ui.config.Theme
import java.awt.Insets
import java.io.File
import javax.swing.JTextField
import javax.swing.filechooser.FileSystemView

class OutputPathField : JTextField() {

    init {
        margin = Insets(0,5,0,5)
        caretColor = Theme.TEXT_COLOR.get()
        text = FileSystemView.getFileSystemView().defaultDirectory.path.plus("\\").plus(Strings.PROGRAM_NAME.get())
    }

    fun getFolder() : File {
        val file = File(text)
        return if (!file.isDirectory) {
            file.parentFile
        } else {
            file
        }
    }

}