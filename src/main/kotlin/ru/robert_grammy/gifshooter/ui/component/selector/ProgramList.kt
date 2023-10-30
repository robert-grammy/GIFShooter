package ru.robert_grammy.gifshooter.ui.component.selector

import ru.robert_grammy.gifshooter.control.ThemeComponent
import ru.robert_grammy.gifshooter.ui.graphics.ListRenderer
import javax.swing.JList

class ProgramList : JList<String>(), ThemeComponent {

    init {
        updateTheme()
    }

    override fun updateTheme() {
        cellRenderer = ListRenderer()
    }

}