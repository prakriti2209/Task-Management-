package com.todo.controllers;

import com.todo.Dtoproj.AdminDTO;
import com.todo.entities.Myuser;
import com.todo.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class Dtocontroller {

    private AdminService dtoservice;


   @GetMapping
   @PreAuthorize("hasRole(\"ADMIN\")")
    public ResponseEntity<?> getdata(){
     AdminDTO admindto= this.dtoservice.getdata();
        return ResponseEntity.ok(admindto);
   }

   @GetMapping("/make-admin/{id}")
   @PreAuthorize("hasRole(\"ADMIN\")")
    public ResponseEntity<?> makeadmin(@PathVariable("id") long id){
       Myuser newadminuser=this.dtoservice.makeadmin(id);
       return ResponseEntity.ok(newadminuser);
   }
   @GetMapping("/remove-admin/{id}")
   @PreAuthorize("hasRole(\"ADMIN\")")
    public ResponseEntity<?> removeadmin(@PathVariable("id") long id){
       Myuser removeuser=this.dtoservice.removeadmin(id);
       return ResponseEntity.ok(removeuser);
   }

}
