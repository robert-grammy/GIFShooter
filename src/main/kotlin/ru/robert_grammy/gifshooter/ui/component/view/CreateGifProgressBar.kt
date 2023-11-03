package ru.robert_grammy.gifshooter.ui.component.view

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.listener.ProgressBarClickListener
import java.awt.Component
import java.io.File
import javax.swing.Box
import javax.swing.JProgressBar

class CreateGifProgressBar(private val folder: File, private val filename: String) : JProgressBar(), LocaleComponent {

    private var framesAmount = 0
    private var handledFrames = 0

    private var state = State.IN_PROCESS
    val separator: Component = Box.createVerticalStrut(5)

    init {
        isStringPainted = true
        string = String.format(Strings.PROGRESS_BAR_RECORD_IN_PROCESS_FORMAT.get(), filename)
        isIndeterminate = true
        state = State.IN_PROCESS
        isBorderPainted = false
    }

    fun handledFramesIncrement() {
        handledFrames++
    }

    fun startPercentCalculateFrame(passedFrames: Int) {
        framesAmount = passedFrames
        isIndeterminate = false
        if (state != State.IN_PROCESS) return
        state = State.SAVING
        string = String.format(Strings.PROGRESS_BAR_RECORD_SAVING_FORMAT.get(), filename, 0)
    }

    fun updatePercent() {
        state = State.SAVING_AND_UPDATE
        framesAmount = if (framesAmount == 0) handledFrames else framesAmount
        val percent: Int = ((handledFrames.toDouble() / framesAmount.toDouble()) * 100).toInt()
        string = String.format(Strings.PROGRESS_BAR_RECORD_SAVING_FORMAT.get(), filename, percent)
        value = percent
    }

    fun finish() {
        state = State.DONE
        value = 100
        string = String.format(Strings.PROGRESS_BAR_RECORD_COMPLETE_FORMAT.get(), filename)
        addMouseListener(ProgressBarClickListener(folder))
    }

    override fun updateTexts() {
        string = when (state) {
            State.IN_PROCESS -> String.format(Strings.PROGRESS_BAR_RECORD_IN_PROCESS_FORMAT.get(), filename)
            State.SAVING -> String.format(Strings.PROGRESS_BAR_RECORD_SAVING_FORMAT.get(), filename, value)
            State.SAVING_AND_UPDATE -> String.format(Strings.PROGRESS_BAR_RECORD_SAVING_FORMAT.get(), filename, value)
            State.DONE -> String.format(Strings.PROGRESS_BAR_RECORD_COMPLETE_FORMAT.get(), filename)
        }
    }

    private enum class State {
        IN_PROCESS,
        SAVING,
        SAVING_AND_UPDATE,
        DONE
    }

}