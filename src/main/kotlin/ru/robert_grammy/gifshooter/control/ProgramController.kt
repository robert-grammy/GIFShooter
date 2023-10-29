package ru.robert_grammy.gifshooter.control

import ru.robert_grammy.gifshooter.ui.component.frame.FreeAreaSelectorFrame
import ru.robert_grammy.gifshooter.ui.free_area.FreeAreaWindow
import ru.robert_grammy.gifshooter.ui.main_window.ProgramWindow
import java.awt.Dimension
import java.awt.Point
import javax.swing.SwingUtilities

object ProgramController {

    private lateinit var mainFrame: ProgramWindow
    private lateinit var freeAreaFrame: FreeAreaWindow
    private lateinit var freeAreaSelectorFrame: FreeAreaSelectorFrame

    var isLoaded = false
        private set

    fun loadProgram() {
        if (isLoaded) return

        MainFrame.load()

        FreeAreaFrame.load()

        FreeAreaSelector.load()

        isLoaded = MainFrame.isLoaded && FreeAreaFrame.isLoaded && FreeAreaSelector.isLoaded
    }

    fun getOutputFolder() = mainFrame.outputFolder

    fun getFPS() = mainFrame.fps

    fun getDelay() = mainFrame.delay

    object MainFrame {

        var isLoaded = false
            private set

        var isVisible = true
            private set

        fun load() {
            if (isLoaded) return
            SwingUtilities.invokeLater {
                mainFrame = ProgramWindow()
                isLoaded = true
            }
        }

        fun show() {
            isVisible = true
            mainFrame.isVisible = true
        }

        fun hide() {
            isVisible = false
            mainFrame.isVisible = false
        }

    }

    object FreeAreaFrame {

        var isLoaded = false
            private set

        var isVisible = false
            private set

        fun load() {
            if (isLoaded) return
            SwingUtilities.invokeLater {
                freeAreaFrame = FreeAreaWindow()
                isLoaded = true
            }
        }

        fun show() {
            isVisible = true
            freeAreaFrame.isVisible = true
        }

        fun hide() {
            isVisible = false
            freeAreaFrame.isVisible = false
        }

        fun setSize(dimension: Dimension) {
            freeAreaFrame.setAreaDimension(dimension)
        }

        fun setLocation(point: Point) {
            freeAreaFrame.setAreaLocation(point)
        }

        fun getLocation() = freeAreaFrame.location

        fun reset() {
            freeAreaFrame.reset()
        }

        fun getArea() = freeAreaFrame.area

    }

    object FreeAreaSelector {

        var isLoaded = false
            private set

        var isVisible = false
            private set

        fun load() {
            if (isLoaded) return
            SwingUtilities.invokeLater {
                freeAreaSelectorFrame = FreeAreaSelectorFrame
                hide()
                isLoaded = true
            }
        }

        fun show() {
            isVisible = true
            freeAreaSelectorFrame.isVisible = true
        }

        fun hide() {
            isVisible = false
            freeAreaSelectorFrame.isVisible = false
        }

        fun clearBackground() {
            freeAreaSelectorFrame.clearBackground()
        }

    }

}