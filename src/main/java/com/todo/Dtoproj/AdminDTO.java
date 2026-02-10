package com.todo.Dtoproj;

import com.todo.entities.Myuser;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
public class AdminDTO {
        private long totalTodos;
        private long totalUsers;
        private long activeUsers;
        private List<Myuser> users;




}
