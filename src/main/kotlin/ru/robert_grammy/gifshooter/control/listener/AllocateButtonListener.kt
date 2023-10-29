package ru.robert_grammy.gifshooter.control.listener

import ru.robert_grammy.gifshooter.control.ProgramController
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

object AllocateButtonListener : ActionListener {

    override fun actionPerformed(e: ActionEvent?) {
        if (e == null) return
        ProgramController.MainFrame.hide()
        ProgramController.FreeAreaFrame.hide()
        ProgramController.FreeAreaSelector.show()
    }

}