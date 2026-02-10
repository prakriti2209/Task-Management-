package com.todo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Myuser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userid;
    private String username;
    private String password;
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> role;
}
