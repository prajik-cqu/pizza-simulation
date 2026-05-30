package cqu.pizza;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.events.OrderEvent;
import cqu.pizza.lifecycle.events.ReportEvent;
import cqu.pizza.simulator.Simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField durationField;

    @FXML
    private TextField fileNameField;

    @FXML
    private TextArea reportArea;

    @FXML
    private void handleRun(ActionEvent event) {
        int duration;

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

        Model model = new Model();
        Simulator simulator = new Simulator(model);
        simulator.initialize(
                new OrderEvent(model.nextRequest()),
                new ReportEvent(duration)
        );
        simulator.run(duration);
    }

    @FXML
    private void handleReset(ActionEvent event) {
        durationField.clear();
        fileNameField.clear();
        reportArea.clear();
    }

    @FXML
    private void handleSave(ActionEvent event) {
        reportArea.setText("Save button clicked");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
}
