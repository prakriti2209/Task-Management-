package com.todo.repo;

import com.todo.entities.Myuser;
import com.todo.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface Todorepo extends JpaRepository<Todo,Long> {
    List<Todo> findTodosByMyuser(Myuser user);

    Optional<Todo> findByIdAndMyuser(long todoId, Myuser user);

    long countDistinctUserByDate(LocalDate now);

    long countByMyuser(Myuser user);

    long countByDateAndMyuser(LocalDate now, Myuser user);

    void deleteByMyuser(Myuser user);
}
