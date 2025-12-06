package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataInitializer {

    public static void insertInitialData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Si ya hay datos, no hacer nada
        Long countCategorias = (Long) session.createQuery("select count(c) from Categoria c").getSingleResult();
        Long countUsuarios = (Long) session.createQuery("select count(u) from Usuario u").getSingleResult();

        if (countCategorias == 0) {
            session.save(new Categoria("Trabajo", "Tareas laborales"));
            session.save(new Categoria("Estudios", "Tareas de clases o exámenes"));
            session.save(new Categoria("Personal", "Cosas de tu día a día"));
            System.out.println("✔ Categorías iniciales insertadas");
        }

        if (countUsuarios == 0) {
            session.save(new Usuario("Lorena", "lorena"));
            session.save(new Usuario("Admin", "admin"));
            System.out.println("✔ Usuarios iniciales insertados");
        }

        tx.commit();
        session.close();
    }
}
