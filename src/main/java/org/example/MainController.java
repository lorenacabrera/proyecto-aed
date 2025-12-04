package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    @FXML
    private TableColumn<Task, String> categoriaColumn;

    @FXML
    private TableColumn<Task, String> usuarioColumn;

    private ObservableList<Task> tasks;

    private final TaskDAO taskDAO = new TaskDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {

        // Columnas básicas
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Columna Categoría
        categoriaColumn.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> {
                            Categoria c = cellData.getValue().getCategoria();
                            return c != null ? c.getNombre() : "";
                        }
                )
        );

        // Columna Usuario
        usuarioColumn.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> {
                            Usuario u = cellData.getValue().getUsuario();
                            return u != null ? u.getUsername() : "";
                        }
                )
        );

        // Cargar datos desde BD
        tasks = FXCollections.observableArrayList(taskDAO.findAll());
        taskTable.setItems(tasks);
    }

    @FXML
    private void onNewTask() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/new-task-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Nueva tarea");
        stage.setScene(new Scene(loader.load()));

        NewTaskController controller = loader.getController();

        controller.setCategorias(FXCollections.observableArrayList(categoriaDAO.findAll()));
        controller.setUsuarios(FXCollections.observableArrayList(usuarioDAO.findAll()));

        stage.showAndWait();

        Task t = controller.getTask();

        if (t != null) {
            taskDAO.save(t);
            tasks.add(t);
        }
    }

    @FXML
    private void onDeleteTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            mostrarAlerta("Debes seleccionar una tarea.");
            return;
        }

        taskDAO.delete(selected);
        tasks.remove(selected);
    }

    @FXML
    private void onEditTask() throws Exception {

        Task selected = taskTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            mostrarAlerta("Debes seleccionar una tarea.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/edit-task-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Editar tarea");
        stage.setScene(new Scene(loader.load()));

        EditTaskController controller = loader.getController();

        controller.setCategorias(FXCollections.observableArrayList(categoriaDAO.findAll()));
        controller.setUsuarios(FXCollections.observableArrayList(usuarioDAO.findAll()));
        controller.setTask(selected);

        stage.showAndWait();

        taskDAO.update(selected);
        taskTable.refresh();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

