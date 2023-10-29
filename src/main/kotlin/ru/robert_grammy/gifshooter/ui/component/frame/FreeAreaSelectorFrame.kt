package ru.robert_grammy.gifshooter.ui.component.frame

import ru.robert_grammy.gifshooter.config.Theme
import ru.robert_grammy.gifshooter.control.listener.AreaMouseSelectorListener
import java.awt.*
import javax.swing.JFrame

object FreeAreaSelectorFrame : JFrame() {

    private fun readResolve(): Any = FreeAreaSelectorFrame

    private val lineWeigh = BasicStroke(2F);

    private val areaMouseSelectorListener = AreaMouseSelectorListener

    init {
        setupFrame()
        loadListeners()
    }

    private fun setupFrame() {
        var rectangle = Rectangle()
        GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.forEach {
            rectangle = rectangle.union(it.defaultConfiguration.bounds)
        }

        isUndecorated = true
        isVisible = true
        clearBackground()
        size = Dimension(rectangle.width, rectangle.height)
        preferredSize = size
    }

    private fun loadListeners() {
        addMouseMotionListener(areaMouseSelectorListener)
        addMouseListener(areaMouseSelectorListener)
        addKeyListener(areaMouseSelectorListener)
    }

    override fun paint(g: Graphics?) {
        val graphics = g as Graphics2D
        if (areaMouseSelectorListener.clicked) {
            graphics.color = Theme.SECONDARY_COLOR.get()
            graphics.stroke = lineWeigh
            graphics.draw(areaMouseSelectorListener.getRectangle())
        }
    }

    fun clearBackground() {
        background = Theme.BACKGROUND_DARKER
        repaint()
    }

}