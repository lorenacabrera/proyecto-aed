package org.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tareas = new ArrayList<>();

    public Usuario() {}

    public Usuario(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Task> getTareas() { return tareas; }
    public void setTareas(List<Task> tareas) { this.tareas = tareas; }

    @Override
    public String toString() {
        return username;
    }
}
