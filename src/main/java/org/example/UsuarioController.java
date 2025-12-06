package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.Usuario;

public class UsuarioController {

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colUsername;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ObservableList<Usuario> usuarios;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre())
        );

        colUsername.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(cd.getValue().getUsername())
        );

        usuarios = FXCollections.observableArrayList(usuarioDAO.findAll());
        tablaUsuarios.setItems(usuarios);
    }

    @FXML
    private void onNuevo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nuevo Usuario");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {

            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setHeaderText("Nuevo Usuario");
            dialog2.setContentText("Username:");

            dialog2.showAndWait().ifPresent(username -> {

                Usuario u = new Usuario(nombre, username);
                usuarioDAO.save(u);
                usuarios.add(u);
            });
        });
    }

    @FXML
    private void onEditar() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        TextInputDialog d1 = new TextInputDialog(seleccionado.getNombre());
        d1.setHeaderText("Editar Nombre");
        d1.setContentText("Nuevo nombre:");

        d1.showAndWait().ifPresent(nombre -> {

            TextInputDialog d2 = new TextInputDialog(seleccionado.getUsername());
            d2.setHeaderText("Editar Usuario");
            d2.setContentText("Nuevo username:");

            d2.showAndWait().ifPresent(username -> {

                seleccionado.setNombre(nombre);
                seleccionado.setUsername(username);
                usuarioDAO.update(seleccionado);
                tablaUsuarios.refresh();
            });
        });
    }

    @FXML
    private void onEliminar() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        usuarioDAO.delete(seleccionado);
        usuarios.remove(seleccionado);
    }
}
