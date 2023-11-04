package ru.robert_grammy.gifshooter.ui.component.selector

import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.ProgramIcon
import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.ThemeComponent
import ru.robert_grammy.gifshooter.ui.graphics.ListRenderer
import ru.robert_grammy.gifshooter.ui.graphics.SelectorUI
import java.awt.Graphics
import javax.swing.BorderFactory
import javax.swing.JComboBox

object DelaySelector : JComboBox<String>(), LocaleComponent, ThemeComponent {

    private fun readResolve(): Any = DelaySelector

    val DELAY_LIST = arrayListOf<Byte>(2, 4, 5, 10, 20, 25, 50, 100)

    private lateinit var selectorUI: SelectorUI

    init {
        minimumSize = ComponentDimension.LINE_HEIGHT_30.get()
    }

    override fun paintComponent(g: Graphics?) {
        if (selectorUI.customPopup.isVisible) {
            selectorUI.customButton.icon = ProgramIcon.DROPDOWN_ACTIVE.getColored(Theme.TEXT_COLOR.hex())
        } else {
            selectorUI.customButton.icon = ProgramIcon.DROPDOWN_DEFAULT.getColored(Theme.TEXT_COLOR.hex())
        }
        super.paintComponent(g)
    }

    override fun updateTexts() {
        removeAllItems()
        DELAY_LIST.forEach {
            addItem(String.format(Strings.DELAY_SELECTOR_FORMAT.get(), it*10))
        }
    }

    override fun updateTheme() {
        border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
        selectorUI = SelectorUI(this as JComboBox<Any>)
        setUI(selectorUI)
        setRenderer(ListRenderer())
    }

}