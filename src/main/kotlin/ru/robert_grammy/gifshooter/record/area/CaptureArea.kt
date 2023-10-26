package ru.robert_grammy.gifshooter.record.area

import java.awt.Robot
import java.awt.image.BufferedImage

interface CaptureArea {

    companion object {
        val ROBOT = Robot()
    }

    fun takeScreenshot() : BufferedImage

}