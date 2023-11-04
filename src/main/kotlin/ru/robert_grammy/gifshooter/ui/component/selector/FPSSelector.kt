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

object FPSSelector : JComboBox<String>(), LocaleComponent, ThemeComponent {

    private fun readResolve(): Any = FPSSelector

    val FRAME_LIST = arrayListOf(50, 25, 20, 10, 5, 4, 2, 1)

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
        for (i in 0 ..< FRAME_LIST.size) {
            addItem(String.format(Strings.FPS_SELECTOR_FORMAT.get(), FRAME_LIST[i], DelaySelector.DELAY_LIST[i]*10))
        }
    }

    override fun updateTheme() {
        border = BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 2)
        selectorUI = SelectorUI(this as JComboBox<Any>)
        setUI(selectorUI)
        setRenderer(ListRenderer())
    }

}