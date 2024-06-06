package me.fortibrine.triangulate.controller

import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Region
import kotlinx.coroutines.*
import kotlin.system.exitProcess

class PainterController {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @FXML
    private lateinit var imageView: ImageView

    @FXML
    private lateinit var rootLayout: Region

    @FXML
    private fun exit() {
        exitProcess(0)
    }

    @FXML
    private fun initialize() {
        this.imageView.fitWidthProperty().bind(this.rootLayout.widthProperty())
        this.imageView.fitHeightProperty().bind(this.rootLayout.heightProperty())

        val images = arrayOf(
            Image("/triangle-1.png"),
            Image("/triangle-2.png"),
            Image("/triangle-3.png"),
            Image("/triangle-4.png"),
            Image("/triangle-5.png")
        )

        scope.launch {
            repeat(1000) {
                imageView.image = images[it % 5]
                delay(200L)
            }
        }
    }

}