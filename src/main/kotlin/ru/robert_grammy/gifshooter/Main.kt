package ru.robert_grammy.gifshooter

import ru.robert_grammy.gifshooter.control.ProgramController
import ru.robert_grammy.gifshooter.record.area.AllDevisesArea
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    ProgramController.loadProgram()
    ImageIO.write(AllDevisesArea.getNewFrame(), "JPG", File("C:\\Users\\Robert\\Desktop\\result\\result.jpg"))
}