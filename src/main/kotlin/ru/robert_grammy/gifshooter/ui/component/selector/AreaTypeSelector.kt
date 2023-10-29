package ru.robert_grammy.gifshooter.ui.component.selector

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.control.LocaleComponent
import ru.robert_grammy.gifshooter.control.ThemeComponent
import ru.robert_grammy.gifshooter.config.ComponentDimension
import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.listener.SelectorCardChangeListener
import ru.robert_grammy.gifshooter.ui.graphics.SelectorListRenderer
import ru.robert_grammy.gifshooter.ui.graphics.SelectorUI
import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Graphics
import javax.swing.BorderFactory
import javax.swing.JComboBox
import javax.swing.JPanel

object AreaTypeSelector : JComboBox<String>(), LocaleComponent, ThemeComponent {

    private fun readResolve(): Any = AreaTypeSelector

    const val SCREEN_CARD = "SCREEN";
    const val FREE_AREA_CARD = "FREE_AREA"

    private val CARDS = arrayOf(SCREEN_CARD, FREE_AREA_CARD)

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

    fun getSelected() = CARDS[selectedIndex]

    override fun updateTexts() {
        removeAllItems()
        addItem(Strings.AREA_SELECTOR_SCREEN_TYPE.get())
        addItem(Strings.AREA_SELECTOR_AREA_TYPE.get())
    }

    override fun updateTheme() {
        selectorUI = SelectorUI(this as JComboBox<Any>)
        setUI(selectorUI)
        setRenderer(SelectorListRenderer())
    }

}