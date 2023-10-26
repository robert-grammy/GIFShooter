package ru.robert_grammy.gifshooter.ui.graphics

import ru.robert_grammy.gifshooter.ui.config.Theme
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer

class SelectorListRenderer : ListCellRenderer<String> {

    override fun getListCellRendererComponent(list: JList<out String>?, value: String?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
        val result = JLabel(" $value ")
        result.isOpaque = true
        result.background = if (isSelected) {
            Theme.HOVER_COLOR.get()
        } else {
            Theme.PRIMARY_COLOR.get()
        }
        return result
    }

}