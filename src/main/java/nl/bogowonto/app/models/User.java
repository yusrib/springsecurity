package nl.bogowonto.app.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    public User() {}

    public User(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    public static final List<User> getUsers() {
        return new ArrayList<>();
    }

}
