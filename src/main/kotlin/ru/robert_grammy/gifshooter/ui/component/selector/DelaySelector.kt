package ru.robert_grammy.gifshooter.ui.component.selector

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.ThemeComponent
import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.ui.graphics.ListRenderer
import ru.robert_grammy.gifshooter.ui.graphics.SelectorUI
import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Graphics
import javax.swing.BorderFactory
import javax.swing.JComboBox

object DelaySelector : JComboBox<String>(), LocaleComponent, ThemeComponent {

    private fun readResolve(): Any = DelaySelector

    val DELAY_LIST = arrayListOf(20, 40, 50, 100, 200, 250, 500, 1000)

    private lateinit var selectorUI: SelectorUI

    init {
        minimumSize = ComponentDimension.LINE_HEIGHT_30.get()
        border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
    }

    override fun paintComponent(g: Graphics?) {
        if (selectorUI.customPopup.isVisible) {
            selectorUI.customButton.icon = ResourceLoader.getIcon("dropdown_active_button_icon.png")
        } else {
            selectorUI.customButton.icon = ResourceLoader.getIcon("dropdown_button_icon.png")
        }
        super.paintComponent(g)
    }

    override fun updateTexts() {
        removeAllItems()
        DELAY_LIST.forEach {
            addItem(String.format(Strings.DELAY_SELECTOR_FORMAT.get(), it))
        }
    }

    override fun updateTheme() {
        selectorUI = SelectorUI(this as JComboBox<Any>)
        setUI(selectorUI)
        setRenderer(ListRenderer())
    }

}