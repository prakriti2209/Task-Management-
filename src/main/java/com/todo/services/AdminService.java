package com.todo.services;

import com.todo.Dtoproj.AdminDTO;
import com.todo.entities.Myuser;
import com.todo.repo.Myrepo;
import com.todo.repo.Todorepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {


    private Myrepo myrepo;
    private Todorepo todorepo;
    private AdminDTO admindto;

    public AdminDTO getdata() {
        admindto.setTotalUsers(this.myrepo.count());
        admindto.setUsers(this.myrepo.findAll());
        admindto.setTotalTodos(this.todorepo.count());
       admindto.setActiveUsers(this.todorepo.countDistinctUserByDate(LocalDate.now()));
       return admindto;
    }

    public Myuser makeadmin(long id) {
        Myuser newadmin=this.myrepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("notfound"));
        List<String> roles=new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        newadmin.setRole(roles);
        return this.myrepo.save(newadmin);

    }

    public Myuser removeadmin(long id) {
        Myuser removeuser=this.myrepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("notfound"));
        if(this.myrepo.countByRole("ADMIN")<=1) {
           throw new UsernameNotFoundException("notfound");
       }
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            removeuser.setRole(roles);
            return this.myrepo.save(removeuser);

    }
}
