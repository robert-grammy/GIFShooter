package ru.robert_grammy.gifshooter.ui.model

import ru.robert_grammy.gifshooter.utils.ResourceLoader
import java.awt.Dimension

enum class ComponentDimension {

    TITLE_BAR;

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

    lateinit var value: Dimension
        private set


}