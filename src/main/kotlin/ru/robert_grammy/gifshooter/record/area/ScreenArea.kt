package ru.robert_grammy.gifshooter.record.area

import java.awt.GraphicsEnvironment
import java.awt.MouseInfo
import java.awt.Rectangle
import java.awt.image.BufferedImage

class ScreenArea(private val rectangle: Rectangle) : CaptureArea {

    override fun takeScreenshot(): BufferedImage {
        return CaptureArea.ROBOT.createScreenCapture(rectangle).apply {
            val mouse = MouseInfo.getPointerInfo().location
            graphics.drawImage(CaptureArea.CURSOR, mouse.x - 7, mouse.y - 4, null)
        }
    }

}