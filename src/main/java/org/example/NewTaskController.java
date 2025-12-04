package org.example;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
        task.setCategoria(categoriaCombo.getValue());
        task.setUsuario(usuarioCombo.getValue());

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
