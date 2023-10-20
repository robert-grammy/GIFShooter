package ru.robert_grammy.gifshooter.ui.component

import ru.robert_grammy.gifshooter.ui.model.Theme
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.ImageIcon
import javax.swing.JButton

class ColoredButton : JButton {

    private var backgroundColor: Color = Theme.PRIMARY_COLOR.value
    private var hoverColor: Color = Theme.HOVER_COLOR.value
    private var activeColor: Color = Theme.ACTIVE_COLOR.value
    private var textColor: Color = Theme.TEXT_COLOR.value
    private var borderColor: Color = Theme.BORDER_COLOR.value

    private var isBordered = false
    private var borderStroke = BasicStroke(0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
    var borderWeight = 0F
        set(value) {
            field = value.coerceAtLeast(0F)
            isBordered = field.toInt() > 0
            borderStroke = BasicStroke(field, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
        }

    init {
        isContentAreaFilled = false
        isBorderPainted = false
        isFocusable = false

        background = backgroundColor
        foreground = textColor
    }

    constructor() : super()
    constructor(text: String) : super(text)
    constructor(text: String, icon: ImageIcon) : super(text, icon)

    override fun paintComponent(g: Graphics?) {
        if (g == null) return
        val model = getModel()
        g.color = backgroundColor
        when {
            model.isPressed -> g.color = activeColor
            model.isRollover -> g.color = hoverColor
        }
        g.fillRect(0,0, width, height)
        if (isBordered) {
            g.color = borderColor
            (g as Graphics2D).stroke = borderStroke
            g.drawRect((borderWeight).toInt(), (borderWeight).toInt(), (width - 2*borderWeight).toInt(), (height - 2*borderWeight).toInt())
        }
        super.paintComponent(g)
    }

}