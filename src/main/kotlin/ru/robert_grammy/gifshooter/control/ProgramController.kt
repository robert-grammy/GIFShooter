package ru.robert_grammy.gifshooter.control

import ru.robert_grammy.gifshooter.ui.free_area.FreeArea
import ru.robert_grammy.gifshooter.ui.main_window.ProgramWindow
import javax.swing.SwingUtilities

object ProgramController {

    private lateinit var frame: ProgramWindow
    var isLoaded = false
        private set

    fun loadProgram() {
        SwingUtilities.invokeLater {
            frame = ProgramWindow()
            isLoaded = true
        }
    }

    fun loadAreaFrame() {
        SwingUtilities.invokeLater {
            FreeArea()
        }
    }

    fun getOutputFolder() = frame.outputFolder

    fun getFPS() = frame.fps

    fun getDelay() = frame.delay

}