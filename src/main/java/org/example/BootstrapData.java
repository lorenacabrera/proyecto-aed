package org.example;

import java.time.LocalDate;

public class BootstrapData {

    public static void crearPorDefecto() {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TaskDAO taskDAO = new TaskDAO();

        // Crear categoría por defecto
        if (categoriaDAO.findAll().isEmpty()) {
            Categoria sinCategoria = new Categoria("Sin categoría", "Categoría por defecto");
            categoriaDAO.save(sinCategoria);
        }

        // Crear usuario por defecto
        if (usuarioDAO.findAll().isEmpty()) {
            Usuario defaultUser = new Usuario("maria", "maria@example.com");
            usuarioDAO.save(defaultUser);
        }

        // Crear tarea de ejemplo si no hay tareas
        if (taskDAO.findAll().isEmpty()) {

            Categoria cat = categoriaDAO.findAll().get(0);
            Usuario u = usuarioDAO.findAll().get(0);

            Task ejemplo = new Task("Comprar pan", "Ir a la panadería", LocalDate.now(), false);
            ejemplo.setCategoria(cat);
            ejemplo.setUsuario(u);

            taskDAO.save(ejemplo);
        }
    }
}
