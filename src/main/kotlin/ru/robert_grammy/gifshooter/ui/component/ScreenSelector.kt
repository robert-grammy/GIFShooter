package ru.robert_grammy.gifshooter.ui.component

import ru.robert_grammy.gifshooter.config.Strings
import ru.robert_grammy.gifshooter.control.LocaleComponent
import java.awt.GraphicsEnvironment
import java.awt.Rectangle
import javax.swing.JComboBox

class ScreenSelector : JComboBox<String>(), LocaleComponent {

    init {
        updateTexts()
        isFocusable = false
    }

    fun getSelected() : Rectangle {
        var rectangle = Rectangle(0,0,0,0)
        if (selectedIndex == 0) {
            for (device in GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices) {
                rectangle = rectangle.union(device.defaultConfiguration.bounds)
            }
        } else {
            rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices[selectedIndex-1].defaultConfiguration.bounds
        }
        return rectangle
    }

    override fun updateTexts() {
        removeAllItems()
        addItem(Strings.SCREEN_SELECTOR_ALL.get())
        for (i in 0 ..< GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.size) {
            addItem(String.format(Strings.SCREEN_SELECTOR_SCREEN_FORMAT.get(), i))
        }
    }

}