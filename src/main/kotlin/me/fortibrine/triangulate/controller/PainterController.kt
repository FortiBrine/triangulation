package me.fortibrine.triangulate.controller

import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import kotlinx.coroutines.*
import me.fortibrine.triangulate.utils.drawTriangle
import kotlin.system.exitProcess

const val COUNT_HORIZONTAL_SQUARES = 10
const val COUNT_VERTICAL_SQUARES = 7
const val GAP = 20

class PainterController {

    private val colors = arrayOf(
        Color.color(0.0784313725490196, 0.7215686274509804, 0.0784313725490196),
        Color.color(0.1411764705882353, 0.6588235294117647, 0.1411764705882353),
        Color.color(0.11372549019607843, 0.5254901960784314, 0.11372549019607843),
        Color.color(0.0, 0.803921568627451, 0.19607843137254902)
    )

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var differentColor = true
    private var animateFlag = true
    private var animateImage = false
    @FXML private lateinit var canvas: Canvas
    @FXML private lateinit var rootLayout: Pane
    @FXML private fun exit(): Unit = exitProcess(0)
    @FXML private fun changeDifferentColor() { differentColor = !differentColor }
    @FXML private fun changeAnimateFlag() { animateFlag = !animateFlag }
    @FXML private fun changeAnimateImage() { animateImage = !animateImage }

    @FXML
    private fun initialize() {

        canvas.widthProperty().bind((canvas.parent as Pane).widthProperty())
        canvas.heightProperty().bind((canvas.parent as Pane).heightProperty())

        val images = (1..17).map { Image("/triangle$it.png") }

        scope.launch {
            delay(1000L)

            val arr = Array(COUNT_VERTICAL_SQUARES) { Array(COUNT_HORIZONTAL_SQUARES) { Array(2) { 0 } } }
            var isGenerated = false
            var stoppedAt = 0

            repeat(Int.MAX_VALUE) {

                canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
//                canvas.graphicsContext2D.drawTriangle(50.0, 50.0, 200.0, 50.0, 50.0, 200.0, Color.BLACK)

                if (animateFlag || !isGenerated) {

                    isGenerated = true

                    val widthSquare = canvas.width / COUNT_HORIZONTAL_SQUARES
                    val heightSquare = canvas.height / COUNT_VERTICAL_SQUARES

                    for (i in 0..<COUNT_VERTICAL_SQUARES) {
                        for (j in 0..<COUNT_HORIZONTAL_SQUARES) {
                            var x = ((j * widthSquare - GAP).toInt()..(j * widthSquare + GAP).toInt()).random()
                            val y = ((i * heightSquare - GAP).toInt()..(i * heightSquare + GAP).toInt()).random()

                            if (j == 0) {
                                x = (j * widthSquare).toInt()
                            }

                            arr[i][j] = arrayOf(x + 20, y + 20)
                        }
                    }
                }

                if (differentColor) {


                    for (i in 0..<(COUNT_VERTICAL_SQUARES-1)) {
                        for (j in 0..<(COUNT_HORIZONTAL_SQUARES-1)) {
                            val a = arr[i][j]
                            val b = arr[i][j + 1]
                            val c = arr[i + 1][j]
                            val d = arr[i + 1][j + 1]

                            if ((i + j) % 2 == 0) {
                                canvas.graphicsContext2D.drawTriangle(a[0], a[1], b[0], b[1], c[0], c[1], Color.WHITE, colors[0])
                                canvas.graphicsContext2D.drawTriangle(b[0], b[1], c[0], c[1], d[0], d[1], Color.WHITE, colors[1])
                            } else {
                                canvas.graphicsContext2D.drawTriangle(a[0], a[1], b[0], b[1], c[0], c[1], Color.WHITE, colors[2])
                                canvas.graphicsContext2D.drawTriangle(b[0], b[1], c[0], c[1], d[0], d[1], Color.WHITE, colors[3])
                            }
                        }
                    }
                } else {
                    canvas.graphicsContext2D.fill = Color.GREEN
                    canvas.graphicsContext2D.fillRect(0.0, 0.0, canvas.width, canvas.height)
                }

                val widthImage = canvas.height * 0.75
                val heightImage = canvas.height * 0.75

                if (animateImage) {
                    stoppedAt = it
                }

//                canvas.graphicsContext2D.drawImage(images[stoppedAt % 18], canvas.width / 2 - widthImage / 2, canvas.height / 2 - heightImage / 2, widthImage, heightImage)
                canvas.graphicsContext2D.drawImage(images[stoppedAt % 17], 0.0, 0.0, canvas.width, canvas.height)

                delay(1000L)
            }
        }

    }

}