package com.todo.controllers;

import com.todo.entities.Myuser;
import com.todo.services.Myservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class Mycontroller {

    private Myservice myservice;
    @PostMapping
    public ResponseEntity<?> savenewuser(@RequestBody Myuser user){
        this.myservice.saveNewuser(user);
        return ResponseEntity.ok(user);
    }
}
