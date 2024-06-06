package me.fortibrine.triangulate.utils

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

fun GraphicsContext.drawTriangle(
    x1: Double,
    y1: Double,
    x2: Double,
    y2: Double,
    x3: Double,
    y3: Double,
    outline: Color,
    fill: Color
) {

    this.fill = fill
    this.stroke = outline

    this.beginPath()
    this.moveTo(x1, y1)
    this.lineTo(x2, y2)
    this.lineTo(x3, y3)
    this.lineTo(x1, y1)
    this.fill()
    this.stroke()

}


fun GraphicsContext.drawTriangle(
    x1: Double,
    y1: Double,
    x2: Double,
    y2: Double,
    x3: Double,
    y3: Double,
    fill: Color
) = drawTriangle(x1, y1, x2, y2, x3, y3, fill, fill)


fun GraphicsContext.drawTriangle(
    x1: Int,
    y1: Int,
    x2: Int,
    y2: Int,
    x3: Int,
    y3: Int,
    outline: Color,
    fill: Color
) {
    drawTriangle(
        x1.toDouble(),
        y1.toDouble(),
        x2.toDouble(),
        y2.toDouble(),
        x3.toDouble(),
        y3.toDouble(),
        outline,
        fill
    )
}