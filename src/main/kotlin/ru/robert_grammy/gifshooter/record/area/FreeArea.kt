package ru.robert_grammy.gifshooter.record.area

import ru.robert_grammy.gifshooter.control.ProgramController
import java.awt.MouseInfo
import java.awt.image.BufferedImage

object FreeArea : CaptureArea {

    override fun takeScreenshot(): BufferedImage {
        return CaptureArea.ROBOT.createScreenCapture(ProgramController.FreeAreaFrame.getArea()).apply {
            val mouse = MouseInfo.getPointerInfo().location
            val x = mouse.x - ProgramController.FreeAreaFrame.getLocation().x - 5
            val y = mouse.y - ProgramController.FreeAreaFrame.getLocation().y - 35
            graphics.drawImage(CaptureArea.CURSOR, x-7, y-4, null)
        }
    }

}