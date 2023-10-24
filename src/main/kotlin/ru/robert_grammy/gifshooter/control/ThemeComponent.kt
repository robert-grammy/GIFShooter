package ru.robert_grammy.gifshooter.control

import java.awt.Component

interface ThemeComponent {

    companion object {
        fun update(component: Component) {
            if (component is ThemeComponent) {
                (component as ThemeComponent).updateTheme()
            }
        }
    }

    fun updateTheme()

}