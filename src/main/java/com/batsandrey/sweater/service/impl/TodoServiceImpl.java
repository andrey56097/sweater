package com.batsandrey.sweater.service.impl;

import com.batsandrey.sweater.dto.TodoDto;
import com.batsandrey.sweater.entity.Manager;
import com.batsandrey.sweater.entity.Todo;
import com.batsandrey.sweater.exception.NoEntityException;
import com.batsandrey.sweater.exception.TodoWithoutManagerException;
import com.batsandrey.sweater.repository.ManagerRepo;
import com.batsandrey.sweater.repository.TodoRepo;
import com.batsandrey.sweater.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private ManagerRepo managerRepo;

    @Override
    public TodoDto createTodo(Todo todo, Long managerId) {
        Manager manager = managerRepo.findById(managerId).orElseThrow(() -> new NoEntityException(managerId));
        todo.setManager(manager);
        return TodoDto.toDto(todoRepo.save(todo));
    }

    @Override
    public TodoDto createTodo(Todo todo) {
        Long managerId = todo.getManager().getId();
        if (managerId == null) {
            throw new TodoWithoutManagerException("Todo without manager id");
        }
        return createTodo(todo, managerId);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return TodoDto.toDto(todoRepo.save(todo));
    }
}
