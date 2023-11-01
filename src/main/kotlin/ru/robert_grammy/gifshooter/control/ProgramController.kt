package ru.robert_grammy.gifshooter.control

import ru.robert_grammy.gifshooter.config.ProgramFont
import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.config.UIProperties
import ru.robert_grammy.gifshooter.record.GifRecorder
import ru.robert_grammy.gifshooter.record.GifWriter
import ru.robert_grammy.gifshooter.record.ScreenTaker
import ru.robert_grammy.gifshooter.ui.component.frame.FreeAreaSelectorFrame
import ru.robert_grammy.gifshooter.ui.component.view.CreateGifProgressBar
import ru.robert_grammy.gifshooter.ui.free_area.FreeAreaWindow
import ru.robert_grammy.gifshooter.ui.main_window.ProgramWindow
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle
import javax.swing.SwingUtilities

object ProgramController {

    private lateinit var mainFrame: ProgramWindow
    private lateinit var freeAreaFrame: FreeAreaWindow
    private lateinit var freeAreaSelectorFrame: FreeAreaSelectorFrame

    private lateinit var lastRecorder: GifRecorder

    var isLoaded = false
        private set

    var isRecording = false
        private set

    fun loadProgram() {
        if (isLoaded) return

        UIProperties.loadProperties()
        UIProperties.loadFont()

        MainFrame.load()
        FreeAreaFrame.load()
        FreeAreaSelector.load()

        isLoaded = MainFrame.isLoaded && FreeAreaFrame.isLoaded && FreeAreaSelector.isLoaded
    }

    fun setLocale(locale: String) {
        Strings.reload(locale)
        ProgramFont.reload()
        mainFrame.updateTexts()
        freeAreaFrame.updateTexts()
    }

    fun setTheme(theme: String) {
        Theme.reload(theme)
        mainFrame.updateTheme()
        freeAreaFrame.updateTheme()
    }

    fun startCapture() {
        if (isRecording) return
        val filename = GifWriter.createFilename()
        val progressBar = CreateGifProgressBar(mainFrame.outputFolder, filename)
        val screenTaker = ScreenTaker(mainFrame.captureArea, mainFrame.fps, progressBar)
        println(mainFrame.delay)
        val gifWriter = GifWriter(mainFrame.delay, mainFrame.outputFolder, filename, screenTaker, progressBar)
        lastRecorder = GifRecorder(screenTaker, gifWriter)

        mainFrame.changeCaptureButtons(true)
        freeAreaFrame.changeCaptureButtons(true)

        isRecording = true
        lastRecorder.startCapture()
    }
    
    fun stopCapture() {
        if (!isRecording) return
        mainFrame.changeCaptureButtons(false)
        if (FreeAreaFrame.isVisible) freeAreaFrame.changeCaptureButtons(false)
        isRecording = false
        lastRecorder.stopCapture()
    }

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

        fun getLocation(): Point = freeAreaFrame.location

        fun reset() {
            freeAreaFrame.reset()
        }

        fun getArea() : Rectangle = freeAreaFrame.area

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