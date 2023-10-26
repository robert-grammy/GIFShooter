package ru.robert_grammy.gifshooter.record.area

import java.awt.image.BufferedImage
import javax.swing.JFrame

class FreeArea(private val frame: JFrame) : CaptureArea {

    override fun takeScreenshot(): BufferedImage {
        return CaptureArea.ROBOT.createScreenCapture(frame.bounds)
    }

}