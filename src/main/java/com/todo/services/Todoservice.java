package com.todo.services;

import com.todo.entities.Myuser;
import com.todo.entities.Todo;
import com.todo.repo.Myrepo;
import com.todo.repo.Todorepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class Todoservice {

    private Todorepo todorepo;
    private Myrepo myrepo;


    public Todo saveNewtodo(Todo todo,String username) {
        Myuser user = this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        todo.setMyuser(user);
        LocalDate ld = LocalDate.now();
        todo.setDate(ld);
        this.todorepo.save(todo);
        return todo;
    }
    public List<Todo> getAlltodos(String username) {
      Myuser user=  this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
      List<Todo> todos=this.todorepo.findTodosByMyuser(user);
      return todos;
    }
    public Todo updatetodo(long todoId,Todo todo, String username){
       Myuser user=this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
       Todo existing= this.todorepo.findByIdAndMyuser(todoId, user).orElseThrow(() -> new EntityNotFoundException("not found"));
       existing.setBody(todo.getBody());
       existing.setTitle(todo.getTitle());
       return this.todorepo.save(existing);

    }
    public void delete(int todoId,String username){
        Myuser user= this.myrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Todo existingtodo= this.todorepo.findByIdAndMyuser(todoId,user).orElseThrow(() -> new EntityNotFoundException("no todo"));
        this.todorepo.delete(existingtodo);
    }
}
