package com.todo.repo;

import com.todo.entities.Myuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Myrepo extends JpaRepository<Myuser,Long> {
     Optional<Myuser> findByUsername(String username);

     int countByRole(String admin);
}
