package ru.robert_grammy.gifshooter.ui.graphics

import ru.robert_grammy.gifshooter.ui.component.ColoredButton
import ru.robert_grammy.gifshooter.ui.config.ComponentDimension
import ru.robert_grammy.gifshooter.ui.config.Theme
import java.awt.Insets
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.plaf.basic.BasicComboBoxUI
import javax.swing.plaf.basic.BasicComboPopup
import javax.swing.plaf.basic.ComboPopup

class SelectorUI(selector: JComboBox<Any>) : BasicComboBoxUI() {

    val customButton = ColoredButton().apply {
        ComponentDimension.SQUARE_BUTTON.setExactly(this)
        hoverColor = Theme.PRIMARY_COLOR.get()
        activeColor = Theme.PRIMARY_COLOR.get()
        isFocusable = false
    }

    val customPopup = BasicComboPopup(selector).apply {
        border = ColoredBorder(Theme.HOVER_COLOR.get(), Insets(0,2,2,2))
    }

    override fun createPopup(): ComboPopup {
        return customPopup
    }

    override fun createArrowButton(): JButton {
        return customButton
    }

}