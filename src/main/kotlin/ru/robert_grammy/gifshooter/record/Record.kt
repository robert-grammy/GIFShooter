package ru.robert_grammy.gifshooter.record

import java.awt.image.BufferedImage

class Record {

    private val queue = ArrayDeque<BufferedImage>()

    @Synchronized
    fun put(image: BufferedImage) {
        queue.add(image)
    }

    @Synchronized
    fun poll() : BufferedImage {
        return queue.removeFirstOrNull()!!
    }

    fun isEmpty() = queue.isEmpty()

    fun isPresent() = queue.isNotEmpty()

}