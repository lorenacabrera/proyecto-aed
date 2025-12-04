package org.example;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditTaskController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private CheckBox doneCheckBox;

    @FXML
    private ComboBox<Categoria> categoriaCombo;

    @FXML
    private ComboBox<Usuario> usuarioCombo;

    private Task task;

    private ObservableList<Categoria> categorias;
    private ObservableList<Usuario> usuarios;

    public void setCategorias(ObservableList<Categoria> categorias) {
        this.categorias = categorias;
        categoriaCombo.setItems(categorias);
    }

    public void setUsuarios(ObservableList<Usuario> usuarios) {
        this.usuarios = usuarios;
        usuarioCombo.setItems(usuarios);
    }

    public void setTask(Task task) {
        this.task = task;

        // Cargar valores actuales en el formulario
        titleField.setText(task.getTitle());
        descriptionField.setText(task.getDescription());
        dueDatePicker.setValue(task.getDueDate());
        doneCheckBox.setSelected(task.isDone());
        categoriaCombo.setValue(task.getCategoria());
        usuarioCombo.setValue(task.getUsuario());
    }

    @FXML
    private void onSave() {
        task.setTitle(titleField.getText());
        task.setDescription(descriptionField.getText());
        task.setDueDate(dueDatePicker.getValue());
        task.setDone(doneCheckBox.isSelected());
        task.setCategoria(categoriaCombo.getValue());
        task.setUsuario(usuarioCombo.getValue());

        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
