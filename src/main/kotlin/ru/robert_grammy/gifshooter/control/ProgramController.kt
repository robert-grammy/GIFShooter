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
import ru.robert_grammy.gifshooter.ui.error_dialog.ErrorDialog.ErrorHandler
import ru.robert_grammy.gifshooter.ui.free_area.FreeAreaWindow
import ru.robert_grammy.gifshooter.ui.main_window.ProgramWindow
import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle
import java.awt.image.BufferedImage
import javax.swing.JProgressBar
import javax.swing.SwingUtilities

object ProgramController {

    private const val ICON_FILENAME = "window_icon.png"
    private val ICON_DIMENSION = Dimension(64, 64)

    private lateinit var mainFrame: ProgramWindow
    private lateinit var freeAreaFrame: FreeAreaWindow
    private lateinit var freeAreaSelectorFrame: FreeAreaSelectorFrame

    private lateinit var lastRecorder: GifRecorder

    private var isLoaded = false

    var isRecording = false
        private set

    fun loadProgram() {
        if (isLoaded) return
        Thread.setDefaultUncaughtExceptionHandler(ErrorHandler())

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
        val gifWriter = GifWriter(mainFrame.delay, mainFrame.outputFolder, filename, screenTaker, progressBar)
        lastRecorder = GifRecorder(screenTaker, gifWriter)

        mainFrame.changeCaptureButtons(true)
        if (FreeAreaFrame.isVisible) freeAreaFrame.changeCaptureButtons(true)
        mainFrame.freeze(true)

        mainFrame.addProgressBar(progressBar)

        isRecording = true
        lastRecorder.startCapture()
    }
    
    fun stopCapture() {
        if (!isRecording) return
        mainFrame.changeCaptureButtons(false)
        if (FreeAreaFrame.isVisible) freeAreaFrame.changeCaptureButtons(false)
        mainFrame.freeze(false)
        isRecording = false
        lastRecorder.stopCapture()
    }

    fun removeProgressBar(progressBar: JProgressBar) {
        mainFrame.removeProgressBar(progressBar)
    }

    fun createFrameIcon() : BufferedImage {
        val resultIcon = BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB)
        val icon = ResourceLoader.getImage(ICON_FILENAME)
        val graphics = resultIcon.graphics

        graphics.color = Theme.SECONDARY_COLOR.get()
        graphics.fillRect(0, 0, ICON_DIMENSION.width, ICON_DIMENSION.height)

        graphics.drawImage(icon, 0, 0, null)
        graphics.dispose()

        return resultIcon
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