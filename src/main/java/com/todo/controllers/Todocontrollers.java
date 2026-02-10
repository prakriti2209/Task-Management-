package com.todo.controllers;

import com.todo.entities.Myuser;
import com.todo.entities.Todo;
import com.todo.services.Todoservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class Todocontrollers {
    private Todoservice todoservice;

    @PostMapping
    @PreAuthorize("hasRole(\"USER\")")
    public ResponseEntity<?> savenewtodo(@RequestBody Todo todo, @AuthenticationPrincipal UserDetails userDetails) {
         Todo newtodo=this.todoservice.saveNewtodo(todo,userDetails.getUsername());
        return ResponseEntity.ok(newtodo);
    }

    @GetMapping
    @PreAuthorize("hasRole(\"USER\")")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserDetails userdetail){
        List<Todo> todolist= this.todoservice.getAlltodos(userdetail.getUsername());
        return ResponseEntity.ok(todolist);

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole(\"USER\")")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Todo todo,@AuthenticationPrincipal UserDetails userdetail){
     Todo updatedtodo=this.todoservice.updatetodo(id,todo, userdetail.getUsername());
        return ResponseEntity.ok(updatedtodo);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole(\"USER\")")
    public ResponseEntity<?> delete(@PathVariable ("id") int id,@AuthenticationPrincipal UserDetails userdetail) {
        this.todoservice.delete(id, userdetail.getUsername());
        return ResponseEntity.noContent().build();
    }

    }



