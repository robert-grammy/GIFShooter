package ru.robert_grammy.gifshooter.record.area

import java.awt.GraphicsEnvironment
import java.awt.MouseInfo
import java.awt.Rectangle
import java.awt.image.BufferedImage

object AllDevisesArea : CaptureArea {

    private val BOUNDS = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.fold(Rectangle(0,0,0,0)) { result, element ->
        result.union(element!!.defaultConfiguration.bounds)
    }

    override fun takeScreenshot(): BufferedImage {
        return CaptureArea.ROBOT.createScreenCapture(BOUNDS).apply {
            val mouse = MouseInfo.getPointerInfo().location
            graphics.drawImage(CaptureArea.CURSOR, mouse.x - 7, mouse.y - 4, null)
            graphics.dispose()
        }
    }

}