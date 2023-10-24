package ru.robert_grammy.gifshooter.ui.component

import java.awt.*
import java.awt.geom.Path2D
import javax.swing.border.AbstractBorder

class ColoredBorder(private val color: Color, private val insets: Insets) : AbstractBorder() {

    override fun paintBorder(c: Component?, g: Graphics?, x: Int, y: Int, width: Int, height: Int) {
        if (c == null) return
        val graphics = g as Graphics2D

        val outer = Rectangle(0, 0, width, height)
        val inner = Rectangle(x + insets.left, y + insets.top, width - (insets.right + insets.left), height - (insets.bottom + insets.top))
        val path: Path2D = Path2D.Float(Path2D.WIND_EVEN_ODD)
        path.append(outer, false)
        path.append(inner, false)
        graphics.color = color
        graphics.fill(path)
    }

    override fun getBorderInsets(c: Component?): Insets {
        return insets
    }

}