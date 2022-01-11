package com.batsandrey.sweater.service;

import com.batsandrey.sweater.dto.TodoDto;
import com.batsandrey.sweater.entity.Todo;

public interface TodoService {
    TodoDto createTodo(Todo todo, Long managerId);
    TodoDto createTodo(Todo todo);
    TodoDto completeTodo(Long id);
}
