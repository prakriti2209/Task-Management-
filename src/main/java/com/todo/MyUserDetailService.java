package com.todo;

import com.todo.entities.Myuser;
import com.todo.repo.Myrepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private Myrepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Myuser myuser= this.repo.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException(username));

       return User.builder()
               .username(myuser.getUsername())
               .password(myuser.getPassword())
               .roles(myuser.getRole().toArray(new String[0])).build();
    }
}
