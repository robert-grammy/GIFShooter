package ru.robert_grammy.gifshooter.record.area

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Robot
import java.awt.image.BufferedImage
import java.awt.image.IndexColorModel

interface CaptureArea {

    companion object {
        val ROBOT = Robot()
        val CURSOR = ResourceLoader.getImage("default_cursor_image.png")
        val COLOR_MODEL: IndexColorModel

        init {
            val reds = ByteArray(128)
            val greens = ByteArray(128)
            val blues = ByteArray(128)

            var nextIndex = 0
            for (r in 15 ..< 256 step 50) {
                for (g in 15 ..< 256 step 50) {
                    for (b in 15 ..< 256 step 50) {
                        reds[nextIndex] = r.toByte()
                        greens[nextIndex] = g.toByte()
                        blues[nextIndex++] = b.toByte()
                    }
                }
            }

            reds[nextIndex] = 0x00.toByte().also { blues[nextIndex] = it }.also { greens[nextIndex++] = it }
            reds[nextIndex] = 0x88.toByte().also { blues[nextIndex] = it }.also { greens[nextIndex++] = it }
            reds[nextIndex] = 0xFF.toByte().also { blues[nextIndex] = it }.also { greens[nextIndex] = it }

            COLOR_MODEL = IndexColorModel(8, 128, reds, greens, blues)
        }
    }

    fun takeScreenshot() : BufferedImage

    fun getNewFrame() : BufferedImage {
        val screenshot = takeScreenshot()
        return BufferedImage(screenshot.width, screenshot.height, BufferedImage.TYPE_BYTE_INDEXED, COLOR_MODEL).apply {
            graphics.drawImage(screenshot, 0, 0, null)
            graphics.dispose()
        }
    }

}