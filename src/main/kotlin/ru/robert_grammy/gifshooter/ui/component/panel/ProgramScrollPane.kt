package ru.robert_grammy.gifshooter.ui.component.panel

import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.ThemeComponent
import ru.robert_grammy.gifshooter.ui.graphics.ProgramScrollBar
import javax.swing.BorderFactory
import javax.swing.JScrollPane

class ProgramScrollPane : JScrollPane(), ThemeComponent {

    init {
        updateTheme()
        isFocusable = false
    }

    override fun updateTheme() {
        setHorizontalScrollBar(ProgramScrollBar(ProgramScrollBar.Orientation.HORIZONTAL))
        setVerticalScrollBar(ProgramScrollBar(ProgramScrollBar.Orientation.VERTICAL))
        viewport.background = Theme.PRIMARY_COLOR.get()
        viewport.foreground = Theme.TEXT_COLOR.get()
        border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
        viewportBorder = BorderFactory.createEmptyBorder()
    }

}