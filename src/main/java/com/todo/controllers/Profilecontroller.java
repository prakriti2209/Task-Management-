package com.todo.controllers;

import com.todo.Dtoproj.UserDto;
import com.todo.entities.Myuser;
import com.todo.services.Myservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class Profilecontroller {
    private Myservice service;

    @GetMapping
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"USER\")")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userdetail) {
        UserDto dto = this.service.getProfile(userdetail.getUsername());
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"USER\")")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserDetails userdetail, @RequestBody Myuser user) {
        Myuser myuser = this.service.updateUser(userdetail.getUsername(), user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"USER\")")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserDetails userdetail) {
        Myuser user = this.service.delete(userdetail.getUsername());
                return ResponseEntity.noContent().build();
    }
}