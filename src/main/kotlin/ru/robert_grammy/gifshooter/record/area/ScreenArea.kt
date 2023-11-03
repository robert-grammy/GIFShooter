package ru.robert_grammy.gifshooter.record.area

import java.awt.MouseInfo
import java.awt.Rectangle
import java.awt.image.BufferedImage

object ScreenArea : CaptureArea {

    var bounds = Rectangle()

    override fun takeScreenshot(): BufferedImage {
        return CaptureArea.ROBOT.createScreenCapture(bounds).apply {
            val mouse = MouseInfo.getPointerInfo().location
            graphics.drawImage(CaptureArea.CURSOR, mouse.x - 7 - bounds.x, mouse.y - 4 - bounds.y, null)
        }
    }

}