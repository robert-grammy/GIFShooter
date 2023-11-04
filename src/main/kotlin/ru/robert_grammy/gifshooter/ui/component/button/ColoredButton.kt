package ru.robert_grammy.gifshooter.ui.component.button

import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.ThemeComponent
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.ImageIcon
import javax.swing.JButton

class ColoredButton : JButton, ThemeComponent, LocaleComponent {

    companion object {
        fun setDefaultStyle(button: JButton) {
            if (button !is ColoredButton) return
            button.borderWeight = 2F
        }

        fun setSquareStyle(button: JButton) {
            setDefaultStyle(button)
            ComponentDimension.SQUARE_BUTTON.setExactly(button)
        }
    }

    lateinit var backgroundColor: Color
    lateinit var hoverColor: Color
    lateinit var activeColor: Color
    lateinit var textColor: Color
    lateinit var borderColor: Color

    private var isBordered = false
    private var borderStroke = BasicStroke(0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
    var borderWeight = 0F
        set(value) {
            field = value.coerceAtLeast(0F)
            isBordered = field.toInt() > 0
            borderStroke = BasicStroke(field, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER)
        }

    private var localeText: Strings? = null

    init {
        isContentAreaFilled = false
        isBorderPainted = false
        isFocusable = false

        updateTheme()

        background = backgroundColor
        foreground = textColor
    }

    constructor() : super()
    constructor(text: String) : super(text)
    constructor(text: String, icon: ImageIcon) : super(text, icon)
    constructor(icon: ImageIcon) : super(icon)
    constructor(localeText: Strings) : super() {
        this.localeText = localeText
    }
    constructor(localeText: Strings, icon: ImageIcon) : super(icon) {
        this.localeText = localeText
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null) return
        val model = getModel()
        if (isBordered) {
            g.color = borderColor
            (g as Graphics2D).stroke = borderStroke
            g.fillRect(0, 0, width, height)
        }
        g.color = backgroundColor
        when {
            model.isPressed -> g.color = activeColor
            model.isRollover -> g.color = hoverColor
        }
        if (!isBordered) {
            g.fillRect(0, 0, width, height)
        } else {
            g.fillRect(borderWeight.toInt(), borderWeight.toInt(), (width-2*borderWeight).toInt(), (height-2*borderWeight).toInt())
        }
        super.paintComponent(g)
    }

    override fun updateTheme() {
        backgroundColor = Theme.PRIMARY_COLOR.get()
        hoverColor = Theme.HOVER_COLOR.get()
        activeColor = Theme.ACTIVE_COLOR.get()
        textColor = Theme.TEXT_COLOR.get()
        borderColor = Theme.BORDER_COLOR.get()
        foreground = Theme.TEXT_COLOR.get()
    }

    override fun updateTexts() {
        if (localeText == null) return
        text = localeText!!.get()
    }

}