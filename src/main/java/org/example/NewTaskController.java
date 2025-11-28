package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NewTaskController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    private Task task;

    @FXML
    private void onSave() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        LocalDate dueDate = dueDatePicker.getValue();

        if (title.isEmpty() || dueDate == null) {
            System.out.println("Faltan campos obligatorios");
            return;
        }

        task = new Task(title, description, dueDate, false);

        // Cierra la ventana
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        task = null;

        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    public Task getTask() {
        return task;
    }
}
