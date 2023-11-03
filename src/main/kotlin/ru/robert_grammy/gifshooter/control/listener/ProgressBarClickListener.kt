package ru.robert_grammy.gifshooter.control.listener

import ru.robert_grammy.gifshooter.control.ProgramController
import java.awt.Desktop
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.JProgressBar

class ProgressBarClickListener(private val folder: File) : MouseAdapter() {

    private var isEntered = false

    override fun mouseEntered(e: MouseEvent?) {
        if (e == null) return
        isEntered = true
    }

    override fun mouseExited(e: MouseEvent?) {
        if (e == null) return
        isEntered = false
    }

    override fun mousePressed(e: MouseEvent?) {
        if (e == null) return
        if (!isEntered) return
        if (e.button == MouseEvent.BUTTON1) {
            Desktop.getDesktop().open(folder)
        }
        if (e.button == MouseEvent.BUTTON3) {
            if (e.source is JProgressBar) {
                ProgramController.removeProgressBar(e.source as JProgressBar)
            }
        }
    }

}