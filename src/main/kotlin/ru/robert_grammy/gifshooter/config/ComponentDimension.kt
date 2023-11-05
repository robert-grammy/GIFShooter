package ru.robert_grammy.gifshooter.config

import java.awt.Component
import java.awt.Dimension

enum class ComponentDimension(val dimension: Dimension) {

    SQUARE_BUTTON(Dimension(30, 30)),
    LINE_HEIGHT_30(Dimension(-1, 30)),
    FILE_CHOOSER_BUTTON(Dimension(100, 30)),
    TEXT_FIELD(Dimension(350, 30));

    fun get() = dimension

    fun setExactly(component: Component) {
        component.preferredSize = dimension
        component.minimumSize = dimension
        component.maximumSize = dimension
    }

}