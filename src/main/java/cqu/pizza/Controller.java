package cqu.pizza;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Report;
import cqu.pizza.lifecycle.ReportException;
import cqu.pizza.lifecycle.events.OrderEvent;
import cqu.pizza.lifecycle.events.ReportEvent;
import cqu.pizza.simulator.Simulator;

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

    /** The report generated after the simulation runs. Null if no simulation has been run. */
    private Report report;

    /**
     * Initializes the controller after the FXML has been loaded.
     *
     * @param url the location used to resolve relative paths (unused)
     * @param rb  the resource bundle (unused)
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        report = null;
    }

    /**
     * Handles the Run button click.
     * Validates the duration input, runs the simulation, and displays the report.
     *
     * @param event the action event
     */
    @FXML
    private void handleRun(ActionEvent event) {
        report = null;
        int duration;

        // Validate duration input
        try {
            duration = Integer.parseInt(durationField.getText().trim());
        } catch (NumberFormatException e) {
            reportArea.setText("Error: Duration must be a positive integer.");
            return;
        }

        if (duration <= 0) {
            reportArea.setText("Error: Duration must be a positive integer.");
            return;
        }

        // Run the simulation
        Model model = new Model();
        Simulator simulator = new Simulator(model);
        simulator.initialize(
                new OrderEvent(model.nextRequest()),
                new ReportEvent(duration)
        );
        simulator.run(duration);

        // Retrieve and display the report
        report = model.getReport();
        if (report != null) {
            reportArea.setText(report.getText());
        }
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
        report = null;
    }

    /**
     * Handles the Save button click.
     * Validates that a report exists and a file name has been entered,
     * then saves the report to the specified file.
     *
     * @param event the action event
     */
    @FXML
    private void handleSave(ActionEvent event) {
        if (report == null) {
            reportArea.setText("Error: No report to save. Please run the simulation first.");
            return;
        }

        String fileName = fileNameField.getText().trim();
        if (fileName.isEmpty()) {
            reportArea.setText("Error: Please enter a file name.");
            return;
        }

        try {
            report.save(fileName);
            reportArea.setText("Report saved to " + fileName);
        } catch (ReportException e) {
            reportArea.setText("Error: " + e.getMessage());
        }
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
