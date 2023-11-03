package ru.robert_grammy.gifshooter.record

import ru.robert_grammy.gifshooter.record.area.CaptureArea
import ru.robert_grammy.gifshooter.ui.component.view.CreateGifProgressBar
import java.util.concurrent.TimeUnit
import kotlin.math.max

class ScreenTaker(private val captureArea: CaptureArea, private val fps: Double, private val progressBar: CreateGifProgressBar) : Runnable {

    private val thread = Thread(this)
    private var wasStarted = false

    var wasStopped = false
        private set

    private lateinit var record: Record

    private var passedFrames = 0

    override fun run() {
        while (!wasStopped) {
            val startTime = System.nanoTime()
            if (!wasStopped) {
                record.put(captureArea.getNewFrame())
                passedFrames++
            } else break
            val endTime = System.nanoTime()
            val elapsed = endTime - startTime
            val sleep = max(0.0, (1000000000 / fps) - elapsed).toLong()
            TimeUnit.NANOSECONDS.sleep(sleep)
        }
    }

    fun start(record: Record) {
        this.record = record
        wasStarted = true
        thread.start()
    }

    fun stop() {
        wasStopped = true
        progressBar.startPercentCalculateFrame(passedFrames)
    }

}