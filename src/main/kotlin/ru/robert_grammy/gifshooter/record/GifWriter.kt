package ru.robert_grammy.gifshooter.record

import ru.robert_grammy.gifshooter.ui.component.view.CreateGifProgressBar
import java.awt.image.BufferedImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageTypeSpecifier
import javax.imageio.metadata.IIOMetadata
import javax.imageio.metadata.IIOMetadataNode
import javax.imageio.stream.FileImageOutputStream
import javax.imageio.stream.ImageOutputStream

class GifWriter(private val delay: Byte, private val outputFolder: File, private val filename: String, private val screenTaker: ScreenTaker, private val progressBar: CreateGifProgressBar) : Runnable {

    companion object {
        private val DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS")
        private const val FILE_NAME_FORMAT = "GIF_SHOOTER_%s"
        private const val GIF_EXTENSION = "gif"

        fun createFilename() = FILE_NAME_FORMAT.format(DATE_FORMAT.format(Date())).plus(".${GIF_EXTENSION}")
    }

    private val nativeWriter = ImageIO.getImageWritersBySuffix(GIF_EXTENSION).next()
    private lateinit var record: Record
    private lateinit var resultFile: File
    private lateinit var metadata: IIOMetadata
    private lateinit var outputStream: ImageOutputStream
    private var wasStarted = false
    private var wasStopped = false

    private val thread = Thread(this)

    override fun run() {
        writeImageToFile()
    }

    fun start(record: Record) {
        if (wasStarted) return
        this.record = record
        wasStarted = true

        createFile()
        setupGifFormat()
        thread.start()
        thread.join()
        saveFile()
    }

    fun stop() {
        wasStopped = true
    }

    private fun createFile() {
        resultFile = File("${outputFolder.path}\\$filename")

        outputFolder.mkdirs()
        resultFile.createNewFile()
    }

    private fun setupGifFormat() {
        val params = nativeWriter.defaultWriteParam
        outputStream = FileImageOutputStream(resultFile)
        nativeWriter.output = outputStream

        val imageTypeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB)
        metadata = nativeWriter.getDefaultImageMetadata(imageTypeSpecifier, params)
        val metaFormatName = metadata.nativeMetadataFormatName
        val root = metadata.getAsTree(metaFormatName) as IIOMetadataNode
        val graphicsControlExtensionNode = getNode(root, "GraphicControlExtension")
        graphicsControlExtensionNode.setAttribute("disposalMethod", "none")
        graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE")
        graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE")
        graphicsControlExtensionNode.setAttribute("delayTime", delay.toString())
        graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0")
        val commentsNode = getNode(root, "CommentExtensions")
        commentsNode.setAttribute("CommentExtension", "Captured by GIF Shooter")
        val appExtensionsNode = getNode(root, "ApplicationExtensions")
        val child = IIOMetadataNode("ApplicationExtension")
        child.setAttribute("applicationID", "NETSCAPE")
        child.setAttribute("authenticationCode", "2.0")
        val applicationData = byteArrayOf(0x1, 0x0, 0x0)
        child.userObject = applicationData
        appExtensionsNode.appendChild(child)
        metadata.setFromTree(metaFormatName, root)

        nativeWriter.prepareWriteSequence(null)
    }

    private fun writeImageToFile() {
        while (!wasStopped) {
            while (!record.isPresent() && !screenTaker.wasStopped) {
                Thread.onSpinWait()
            }
            while (record.isPresent()) {
                val image = record.poll()
                nativeWriter.writeToSequence(IIOImage(image, null, metadata), null)
                progressBar.handledFramesIncrement()
                if (screenTaker.wasStopped) progressBar.updatePercent()
            }
        }
    }

    private fun saveFile() {
        nativeWriter.endWriteSequence()
        outputStream.close()
        progressBar.finish()
    }

    private fun getNode(rootNode: IIOMetadataNode, nodeName: String): IIOMetadataNode {
        for (i in 0 ..< rootNode.length) {
            if (rootNode.item(i).nodeName.equals(nodeName, true)) {
                return rootNode.item(i) as IIOMetadataNode
            }
        }
        val node = IIOMetadataNode(nodeName)
        rootNode.appendChild(node)
        return node
    }

}