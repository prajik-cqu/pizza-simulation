package cqu.pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Entry point for the Pizza Kitchen Simulation application.
 * Loads the JavaFX GUI from the FXML file and displays the main stage.
 *
 * @author student
 */
public class App extends Application {

    /**
     * Starts the JavaFX application by loading the FXML layout and showing the stage.
     *
     * @param stage the primary stage provided by JavaFX
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        URL fxml = getClass().getResource("simulation.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        Scene scene = new Scene(loader.load());
        stage.setTitle("Simulation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Main method – launches the JavaFX application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
