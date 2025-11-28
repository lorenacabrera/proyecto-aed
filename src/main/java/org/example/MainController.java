package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

        // Vincular columnas con las propiedades de la clase Task
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Lista de tareas de ejemplo
        tasks = FXCollections.observableArrayList(
                new Task("Comprar pan", "Ir a la panadería", LocalDate.now(), false),
                new Task("Estudiar JavaFX", "Hacer el proyecto", LocalDate.now().plusDays(1), true)
        );

        // Asignar la lista a la tabla
        taskTable.setItems(tasks);
    }

    @FXML
    private void onNewTask() throws Exception {

        // Cargar la ventana de nueva tarea
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/new-task-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Nueva tarea");
        stage.setScene(new Scene(loader.load()));

        // Mostrar como ventana modal
        stage.showAndWait();

        // Obtener el resultado del controlador hijo
        NewTaskController controller = loader.getController();
        Task t = controller.getTask();

        // Si el usuario pulsó Guardar, añadir la tarea
        if (t != null) {
            tasks.add(t);
        }
    }
}
