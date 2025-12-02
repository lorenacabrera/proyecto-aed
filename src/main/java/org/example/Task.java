package org.example;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // el convertidor LocalDateAttributeConverter se aplicará automáticamente si lo añadiste
    private LocalDate dueDate;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Task(){}

    public Task(String title, String description, LocalDate dueDate, boolean done) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
    }

    // Getters y Setters (inc. id, categoria, usuario)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getStatus() {
        return done ? "Hecha" : "Pendiente";
    }

    @Override
    public String toString() {
        return title;
    }
}
