package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class MainController {

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;

    @FXML
    private TableColumn<Task, String> statusColumn;

    private ObservableList<Task> tasks;

    @FXML
    public void initialize() {

        // Vincular columnas con propiedades de Task
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Crear lista de tareas (provisional, antes de usar base de datos)
        tasks = FXCollections.observableArrayList(
                new Task("Comprar pan", "Ir a la panadería", LocalDate.now(), false),
                new Task("Estudiar JavaFX", "Hacer el proyecto", LocalDate.now().plusDays(1), true)
        );

        // Enlazar lista con la tabla
        taskTable.setItems(tasks);
    }
}
