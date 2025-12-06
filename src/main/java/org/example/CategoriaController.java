package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CategoriaController {

    @FXML private TableView<Categoria> tablaCategorias;
    @FXML private TableColumn<Categoria, String> colNombre;
    @FXML private TableColumn<Categoria, String> colDescripcion;

    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ObservableList<Categoria> categorias;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
        colDescripcion.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getDescripcion()));

        categorias = FXCollections.observableArrayList(categoriaDAO.findAll());
        tablaCategorias.setItems(categorias);
    }

    @FXML
    private void onNueva() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nueva Categoría");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            Categoria c = new Categoria(nombre, "");
            categoriaDAO.save(c);
            categorias.add(c);
        });
    }

    @FXML
    private void onEditar() {
        Categoria seleccionada = tablaCategorias.getSelectionModel().getSelectedItem();
        if (seleccionada == null) return;

        TextInputDialog dialog = new TextInputDialog(seleccionada.getNombre());
        dialog.setHeaderText("Editar Categoría");
        dialog.setContentText("Nuevo nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            seleccionada.setNombre(nombre);
            categoriaDAO.update(seleccionada);
            tablaCategorias.refresh();
        });
    }

    @FXML
    private void onEliminar() {
        Categoria seleccionada = tablaCategorias.getSelectionModel().getSelectedItem();
        if (seleccionada == null) return;

        categoriaDAO.delete(seleccionada);
        categorias.remove(seleccionada);
    }
}
