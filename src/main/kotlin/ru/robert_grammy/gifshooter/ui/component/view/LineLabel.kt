package ru.robert_grammy.gifshooter.ui.component.view

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.control.LocaleComponent
import javax.swing.JLabel

class LineLabel(private val localeText: Strings) : JLabel(), LocaleComponent {

    override fun updateTexts() {
        text = localeText.get()
    }

}