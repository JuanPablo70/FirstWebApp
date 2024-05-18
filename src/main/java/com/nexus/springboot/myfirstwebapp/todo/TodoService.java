package com.nexus.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    static {
        todos.add(new Todo(1, "admin", "Learn Angular", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(2, "admin", "Learn MongoDB", LocalDate.now().plusYears(2), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }

}
