package ru.robert_grammy.gifshooter.record.area

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Robot
import java.awt.image.BufferedImage

interface CaptureArea {

    companion object {
        val ROBOT = Robot()
        val CURSOR = ResourceLoader.getImage("default_cursor_image.png")
    }

    fun takeScreenshot() : BufferedImage

}