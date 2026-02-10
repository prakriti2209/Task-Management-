package com.todo.Dtoproj;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDto {
    private long id;
    private String username;
    private long todaytodos;
    private long totaltodos;
    private boolean activeStatus;
}
