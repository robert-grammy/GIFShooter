package ru.robert_grammy.gifshooter.control.listener

import ru.robert_grammy.gifshooter.control.ProgramController.isRecording
import ru.robert_grammy.gifshooter.control.ProgramController.startCapture
import ru.robert_grammy.gifshooter.control.ProgramController.stopCapture
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

object CaptureButtonListener : ActionListener {

    override fun actionPerformed(e: ActionEvent?) {
        if (e == null) return
        if (isRecording) {
            stopCapture()
        } else {
            startCapture()
        }
    }

}