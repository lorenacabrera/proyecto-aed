package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {

    @FXML
    private TableView<?> taskTable;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> dueDateColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    public void initialize() {
        System.out.println("MainController inicializado correctamente.");
    }
}
