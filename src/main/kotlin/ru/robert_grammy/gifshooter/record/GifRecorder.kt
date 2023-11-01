package ru.robert_grammy.gifshooter.record

class GifRecorder(private val taker: ScreenTaker, private val writer: GifWriter) {

    private val startThread: Thread
    private val stopThread: Thread
    private var wasStarted = false
    private var isRecorded = false
    val record = Record()

    init {
        startThread = Thread {
            taker.start(record)
            writer.start(record)
        }

        stopThread = Thread {
            taker.stop()
            writer.stop()
        }
    }

    fun startCapture() {
        if (wasStarted) return
        startThread.start()
        wasStarted = true
    }

    fun stopCapture() {
        if (isRecorded) return
        stopThread.start()
        isRecorded = true
    }

}