package ru.robert_grammy.gifshooter.ui.graphics

import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.ThemeComponent
import java.awt.Insets
import javax.swing.JScrollBar

class ProgramScrollBar(private val barOrientation: Orientation) : JScrollBar(), ThemeComponent {

    init {
        updateTheme()
    }

    override fun updateTheme() {
        setUI(ScrollUI())
        border = ColoredBorder(Theme.BORDER_COLOR.get(), barOrientation.getInsets())
    }

    enum class Orientation(private val borderInsets: Insets) {
        VERTICAL(Insets(1,0,0,0)),
        HORIZONTAL(Insets(0,1,0,0));

        fun getInsets() = borderInsets
    }

}