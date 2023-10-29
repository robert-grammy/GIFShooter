package ru.robert_grammy.gifshooter.control.listener

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

class AreaMouseSelectorListener() : MouseAdapter(), KeyListener {

    var firstX = -1
        private set
    var firstY = -1
        private set
    var secondX = -1
        private set
    var secondY = -1
        private set

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
        // window.areaSelectorFrame.hideSelectorFrame() - скрыть окно выделения
        var areaDimension = Dimension(area.width+8, area.height+8)
        // window.freeAreaFrame.captureArea.preferredSize = areaDimension - указать размеры
        // window.freeAreaFrame.captureArea.setSize(areaDimension.width, areaDimension.height) - Указать размеры
        // window.freeAreaFrame.frame.pack() - упаковка
        // window.freeAreaFrame.frame.location = Point(area.x-5, area.y-35) - установить локацию
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
        //window.areaSelectorFrame.clearBackground()
    }

    fun getRectangle() = Rectangle(min(firstX, secondX), min(firstY, secondY), max(abs(firstX-secondX), 300), max(abs(firstY-secondY), 30))


    override fun keyPressed(e: KeyEvent?) {
        if (e == null) return
//        if (window.areaSelectorFrame.isVisible && e.keyCode == KeyEvent.VK_ESCAPE) {
//            window.areaSelectorFrame.hideSelectorFrame()
//        }
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}
}