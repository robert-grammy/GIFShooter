package ru.robert_grammy.gifshooter.record.area

import java.awt.GraphicsEnvironment
import java.awt.MouseInfo
import java.awt.Rectangle
import java.awt.image.BufferedImage

class ScreenArea(monitorId: Int) : CaptureArea {

    private var screenRectangle: Rectangle = Rectangle(0, 0, 0, 0)

    init {
        if (monitorId == 0) {
            for (device in GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices) {
                screenRectangle = screenRectangle.union(device.defaultConfiguration.bounds)
            }
        } else {
            screenRectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices[monitorId - 1].defaultConfiguration.bounds
        }
    }

    override fun takeScreenshot(): BufferedImage {
        return CaptureArea.ROBOT.createScreenCapture(screenRectangle).apply {
            val mouse = MouseInfo.getPointerInfo().location
            graphics.drawImage(CaptureArea.CURSOR, mouse.x - 7, mouse.y - 4, null)
        }
    }

}