package ru.robert_grammy.gifshooter.ui.component

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.control.LocaleComponent
import javax.swing.JComboBox

class AreaTypeSelector : JComboBox<String>(), LocaleComponent {

    companion object {
        private val CARDS = arrayOf("SCREEN", "AREA")
    }

    init {
        updateTexts()
        isFocusable = false
    }

    fun getSelected() = CARDS[selectedIndex]

    override fun updateTexts() {
        removeAllItems()
        addItem(Strings.AREA_SELECTOR_SCREEN_TYPE.get())
        addItem(Strings.AREA_SELECTOR_AREA_TYPE.get())
    }

}