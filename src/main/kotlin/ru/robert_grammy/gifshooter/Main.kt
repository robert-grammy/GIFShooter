package ru.robert_grammy.gifshooter

import ru.robert_grammy.gifshooter.ui.view.MainFrame
import java.util.concurrent.TimeUnit
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    SwingUtilities.invokeLater {
        val window = MainFrame()
        Thread {
            TimeUnit.SECONDS.sleep(2)
            SwingUtilities.invokeLater {
                window.setStringsLocale("en_US")
                window.setColorTheme("red")
            }
        }.start()
    }
}