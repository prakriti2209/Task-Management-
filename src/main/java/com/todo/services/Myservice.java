package com.todo.services;

import com.todo.Dtoproj.UserDto;
import com.todo.MyUserDetailService;
import com.todo.entities.Myuser;
import com.todo.repo.Myrepo;
import com.todo.repo.Todorepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class Myservice {
    private Myrepo myrepo;
    private PasswordEncoder passwordencoder;
    private UserDto userdto;
    private Todorepo todorepo;

    public Myuser saveNewuser(Myuser myuser) {
        myuser.setPassword(passwordencoder.encode(myuser.getPassword()));
        myuser.setRole(List.of("USER"));
        return this.myrepo.save(myuser);
    }


    public UserDto getProfile(String username) {
        Myuser user = this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        userdto.setId(user.getUserid());
        userdto.setUsername(user.getUsername());
        userdto.setTotaltodos(this.todorepo.countByMyuser(user));
        long count = (this.todorepo.countByDateAndMyuser(LocalDate.now(), user));
        userdto.setTodaytodos(count);
        userdto.setActiveStatus(count >= 1 ? true : false);
        return userdto;
    }

    public Myuser updateUser(String username, Myuser newuser) {

        Myuser user = this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        user.setPassword(this.passwordencoder.encode(newuser.getPassword()));
        user.setUsername(newuser.getUsername());
        return this.myrepo.save(user);

    }

    public Myuser delete(String username) {
        Myuser user = this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (this.myrepo.countByRole("ADMIN") <= 1) {
            throw new UsernameNotFoundException(username);
        }

        this.todorepo.deleteByMyuser(user);
        this.myrepo.deleteById(user.getUserid());
        return user;
    }
}