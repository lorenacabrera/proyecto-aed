package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditTaskController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private CheckBox doneCheckBox;

    private Task task;

    public void setTask(Task t) {
        this.task = t;
        titleField.setText(t.getTitle());
        descriptionField.setText(t.getDescription());
        dueDatePicker.setValue(t.getDueDate());
        doneCheckBox.setSelected(t.isDone());
    }

    @FXML
    private void onSave() {
        task.setTitle(titleField.getText());
        task.setDescription(descriptionField.getText());
        task.setDueDate(dueDatePicker.getValue());
        task.setDone(doneCheckBox.isSelected());

        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
