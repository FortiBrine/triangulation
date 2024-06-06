package me.fortibrine.triangulate

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class App: Application() {

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(this.javaClass.getResource("/painter.fxml"))
        val root = fxmlLoader.load<Parent>()

        stage.scene = Scene(root)
        stage.title = "Геометрія"
        stage.show()

    }

}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}