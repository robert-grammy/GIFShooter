package ru.robert_grammy.gifshooter.control

import java.awt.Component

interface LocaleComponent {

    companion object {
        fun update(component: Component) {
            if (component is LocaleComponent) {
                (component as LocaleComponent).updateTexts()
            }
        }
    }

    fun updateTexts()

}