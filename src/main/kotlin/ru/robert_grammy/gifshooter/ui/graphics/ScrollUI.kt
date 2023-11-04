package ru.robert_grammy.gifshooter.ui.graphics

import ru.robert_grammy.gifshooter.config.ProgramIcon
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.ui.component.button.ColoredButton
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JScrollBar
import javax.swing.plaf.basic.BasicScrollBarUI

class ScrollUI : BasicScrollBarUI() {

    override fun paintTrack(g: Graphics?, c: JComponent?, trackBounds: Rectangle?) {
        if (g == null || c == null || trackBounds == null) return
        super.paintTrack(g, c, trackBounds)
        g.color = Theme.HOVER_COLOR.get()
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height)
    }

    override fun paintThumb(g: Graphics?, c: JComponent?, thumbBounds: Rectangle?) {
        if (g == null || c == null || thumbBounds == null) return
        super.paintTrack(g, c, thumbBounds)
        g.color = Theme.ACTIVE_COLOR.get()
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height)
    }

    override fun paintIncreaseHighlight(g: Graphics?) {
        if (g == null) return
        val insets = scrollbar.getInsets()
        val thumbRectangle = thumbBounds
        g.color = Theme.SECONDARY_COLOR.get()

        if (scrollbar.orientation == JScrollBar.VERTICAL) {
            val x = insets.left
            val y = thumbRectangle.y + thumbRectangle.height
            val width = scrollbar.width - (insets.left + insets.right)
            val height = trackRect.y + trackRect.height - y
            g.fillRect(x, y, width, height)
        } else {
            val x: Int
            val width: Int
            if (scrollbar.componentOrientation.isLeftToRight) {
                x = thumbRectangle.x + thumbRectangle.width
                width = trackRect.x + trackRect.width - x
            } else {
                x = trackRect.x
                width = thumbRectangle.x - x
            }
            val y = insets.top
            val height = scrollbar.height - (insets.top + insets.bottom)
            g.fillRect(x, y, width, height)
        }
    }

    override fun paintDecreaseHighlight(g: Graphics?) {
        if (g == null) return
        val insets = scrollbar.getInsets()
        val thumbRectangle = thumbBounds
        g.color = Theme.SECONDARY_COLOR.get()

        if (scrollbar.orientation == JScrollBar.VERTICAL) {
            val x = insets.left
            val y = trackRect.y
            val width = scrollbar.width - (insets.left + insets.right)
            val height = thumbRectangle.y - y
            g.fillRect(x, y, width, height)
        } else {
            val x: Int
            val width: Int
            if (scrollbar.componentOrientation.isLeftToRight) {
                x = trackRect.x
                width = thumbRectangle.x - x
            } else {
                x = thumbRectangle.x + thumbRectangle.width
                width = trackRect.x + trackRect.width - x
            }
            val y = insets.top
            val height = scrollbar.height - (insets.top + insets.bottom)
            g.fillRect(x, y, width, height)
        }
    }

    override fun createIncreaseButton(orientation: Int): JButton {
        return ColoredButton(ProgramIcon.DROPDOWN_DEFAULT.getColored(Theme.TEXT_COLOR.hex())).apply {
            preferredSize = Dimension(20,20)
        }
    }

    override fun createDecreaseButton(orientation: Int): JButton {
        return ColoredButton(ProgramIcon.DROPDOWN_ACTIVE.getColored(Theme.TEXT_COLOR.hex())).apply {
            preferredSize = Dimension(20,20)
        }
    }

}