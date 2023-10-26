package ru.robert_grammy.gifshooter.control.listener

import ru.robert_grammy.gifshooter.ui.component.selector.AreaTypeSelector
import java.awt.CardLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel

class SelectorCardChangeListener(private val areaTypeSelector: AreaTypeSelector, private val areaOptionPane: JPanel) : ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        if (e == null) return
        if (areaTypeSelector.getSelectedIndex() == -1) return
        val card = areaTypeSelector.getSelected()
        val layout = areaOptionPane.layout as CardLayout
        layout.show(areaOptionPane, card)
    }

}