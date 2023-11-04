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
import java.awt.GraphicsEnvironment
import java.awt.Rectangle
import javax.swing.BorderFactory
import javax.swing.JComboBox

object ScreenSelector : JComboBox<String>(), LocaleComponent, ThemeComponent {

    private fun readResolve(): Any = ScreenSelector

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

    fun getSelectedScreen() : Rectangle {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices[selectedIndex-1].defaultConfiguration.bounds
    }

    override fun updateTexts() {
        removeAllItems()
        addItem(Strings.SCREEN_SELECTOR_ALL.get())
        for (i in 0 ..< GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.size) {
            addItem(String.format(Strings.SCREEN_SELECTOR_SCREEN_FORMAT.get(), i))
        }
    }

    override fun updateTheme() {
        border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
        selectorUI = SelectorUI(this as JComboBox<Any>)
        setUI(selectorUI)
        setRenderer(ListRenderer())
    }

}