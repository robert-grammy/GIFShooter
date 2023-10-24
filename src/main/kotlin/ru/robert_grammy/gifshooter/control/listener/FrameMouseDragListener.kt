package ru.robert_grammy.gifshooter.control.listener

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame

class FrameMouseDragListener(frame: JFrame) : MouseAdapter() {

    private var x = -1
    private var y = -1

    private val frame : JFrame

    init {
        this.frame = frame
    }

    override fun mouseMoved(e: MouseEvent?) {
        if (e == null) return
        x = e.x
        y = e.y
    }

    override fun mouseDragged(e: MouseEvent?) {
        if (e == null) return
        frame.setLocation(
            frame.location.x + e.x - x,
            frame.location.y + e.y - y
        )
    }

}