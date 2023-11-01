package ru.robert_grammy.gifshooter.ui.component.view

import ru.robert_grammy.gifshooter.control.listener.ProgressBarClickListener
import java.io.File
import javax.swing.JProgressBar

class CreateGifProgressBar(private val folder: File, private val filename: String) : JProgressBar() {

    private var framesAmount = 0
    private var handledFrames = 0
    private var frameAfterRecord = 0

    init {
        isStringPainted = true
        string = "$filename - в процессе записи" //TODO(вынести в локаль)
        isIndeterminate = true
    }

    fun amountFramesIncrement() {
        framesAmount++
    }

    fun handledFramesIncrement() {
        handledFrames++
    }

    fun startPercentCalculateFrame() {
        frameAfterRecord = handledFrames
        isIndeterminate = false
        string = "$filename сохраняется (0%)"
    }

    fun updatePercent() {
        val handled: Double = (handledFrames - frameAfterRecord).toDouble()
        val amount: Double = (framesAmount - frameAfterRecord).toDouble()
        val percent: Int = ((handled / amount) * 100).toInt()
        string = "$filename сохраняется ($percent%)" //TODO(вынести в локаль)
        value = percent
    }

    fun finish() {
        value = 100
        string = filename
        addMouseListener(ProgressBarClickListener(folder))
    }

}