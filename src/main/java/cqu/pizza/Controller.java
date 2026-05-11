package cqu.pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Pizza Kitchen Simulation GUI.
 * Handles all button events from the JavaFX FXML view.
 *
 * @author student
 */
public class Controller implements Initializable {

    /** Text field for the simulation duration input. */
    @FXML
    private TextField durationField;

    /** Text field for the report file name input. */
    @FXML
    private TextField fileNameField;

    /** Text area where the simulation report is displayed. */
    @FXML
    private TextArea reportArea;

    /**
     * Initializes the controller after the FXML has been loaded.
     *
     * @param url the location used to resolve relative paths (unused)
     * @param rb  the resource bundle (unused)
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // nothing to initialise in phase 1
    }

    /**
     * Handles the Run button click.
     * In phase 1 this displays a stub message.
     *
     * @param event the action event
     */
    @FXML
    private void handleRun(ActionEvent event) {
        reportArea.setText("Run button handler invoked.");
    }

    /**
     * Handles the Reset button click.
     * Clears the duration field, file name field and the report area.
     *
     * @param event the action event
     */
    @FXML
    private void handleReset(ActionEvent event) {
        durationField.clear();
        fileNameField.clear();
        reportArea.clear();
    }

    /**
     * Handles the Save button click.
     * In phase 1 this displays a stub message.
     *
     * @param event the action event
     */
    @FXML
    private void handleSave(ActionEvent event) {
        reportArea.setText("Save button handler invoked.");
    }

    /**
     * Handles the Exit button click.
     * Closes the application.
     *
     * @param event the action event
     */
    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
}
