package ru.robert_grammy.gifshooter.record

import ru.robert_grammy.gifshooter.record.area.CaptureArea
import ru.robert_grammy.gifshooter.ui.component.view.CreateGifProgressBar

class ScreenTaker(private val captureArea: CaptureArea, private val fps: Double, private val progressBar: CreateGifProgressBar) : Runnable {

    companion object {
        private const val SECOND = 1000000000L
    }

    private val thread = Thread(this)
    private var wasStarted = false
    var isRecorded = false
        private set
    private lateinit var record: Record

    override fun run() {
        var delta = 0.0
        var lastTime = System.nanoTime()
        while (!Thread.currentThread().isInterrupted) {
            val now = System.nanoTime()
            val elapsedTime = now - lastTime
            lastTime = now
            delta += elapsedTime / (SECOND / fps)
            while (delta > 1) {
                record.put(captureArea.takeScreenshot())
                progressBar.amountFramesIncrement()
                delta--
            }
        }
        isRecorded = true
    }

    fun start(record: Record) {
        if (wasStarted) return
        wasStarted = true
        this.record = record
        thread.start()
    }

    fun stop() {
        if (isRecorded) return
        thread.interrupt()
    }

}