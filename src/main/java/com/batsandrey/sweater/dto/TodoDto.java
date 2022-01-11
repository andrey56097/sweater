package com.batsandrey.sweater.dto;

import com.batsandrey.sweater.entity.Todo;

public class TodoDto {
    private Long id;
    private String title;
    private Boolean completed;

    public TodoDto() {
    }

    public static TodoDto toDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setCompleted(todo.getCompleted());
        todoDto.setTitle(todo.getTitle());
        return todoDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
