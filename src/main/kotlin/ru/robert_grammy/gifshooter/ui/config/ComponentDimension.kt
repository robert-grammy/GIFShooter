package ru.robert_grammy.gifshooter.ui.config

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Component
import java.awt.Dimension

enum class ComponentDimension {

    SQUARE_BUTTON,
    LINE_HEIGHT_30;

    companion object {
        private const val WIDTH_AND_HEIGHT_SEPARATOR = "x"
        private val CONFIG = ResourceLoader.getProperties("component_sizes.properties")

        init {
            entries.forEach {
                val dimensionParts = CONFIG.getProperty(it.name.lowercase()).split(WIDTH_AND_HEIGHT_SEPARATOR)
                it.value = Dimension(dimensionParts[0].toInt(), dimensionParts[1].toInt())
            }
        }
    }

    private lateinit var value: Dimension

    fun get() = value

    fun setExactly(component: Component) {
        component.preferredSize = value
        component.minimumSize = value
        component.maximumSize = value
    }

}