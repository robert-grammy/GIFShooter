package ru.robert_grammy.gifshooter.control.listener

import ru.robert_grammy.gifshooter.control.ProgramController
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object AreaMouseSelectorListener : MouseAdapter(), KeyListener {

    private var firstX = -1
    private var firstY = -1
    private var secondX = -1
    private var secondY = -1

    var clicked = false
        private set

    override fun mouseReleased(e: MouseEvent?) {
        if (e == null) return
        val area = getRectangle()
        firstX = -1
        firstY = -1
        secondX = -1
        secondY = -1
        clicked = false
        ProgramController.FreeAreaSelector.hide()
        ProgramController.MainFrame.show()
        ProgramController.FreeAreaFrame.show()
        val areaDimension = Dimension(area.width, area.height)
        ProgramController.FreeAreaFrame.setSize(areaDimension)
        ProgramController.FreeAreaFrame.setLocation(Point(area.x-5, area.y-35))
    }

    override fun mouseDragged(e: MouseEvent?) {
        if (e == null) return
        if (!clicked) {
            firstX = e.x
            firstY = e.y
        }
        clicked = true
        secondX = e.x
        secondY = e.y
        ProgramController.FreeAreaSelector.clearBackground()
    }

    fun getRectangle() = Rectangle(min(firstX, secondX), min(firstY, secondY), max(abs(firstX-secondX), 300), max(abs(firstY-secondY), 30))


    override fun keyPressed(e: KeyEvent?) {
        if (e == null) return
        if (ProgramController.FreeAreaSelector.isVisible && e.keyCode == KeyEvent.VK_ESCAPE) {
            ProgramController.FreeAreaSelector.hide()
            ProgramController.MainFrame.show()
            ProgramController.FreeAreaFrame.show()
        }
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}
}