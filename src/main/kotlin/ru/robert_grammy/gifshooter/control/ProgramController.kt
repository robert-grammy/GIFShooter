package ru.robert_grammy.gifshooter.control

import ru.robert_grammy.gifshooter.ui.MainFrame
import javax.swing.SwingUtilities

object ProgramController {

    private lateinit var frame: MainFrame
    var isLoaded = false
        private set

    fun loadProgram() {
        SwingUtilities.invokeLater {
            frame = MainFrame()
            isLoaded = true
        }
    }

    fun getOutputFolder() = frame.outputFolder

    fun getFPS() = frame.fps

    fun getDelay() = frame.delay

}