package com.batsandrey.sweater.consumer;

import com.batsandrey.sweater.entity.Todo;
import com.batsandrey.sweater.exception.TodoProcessingException;
import com.batsandrey.sweater.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TodosPackageListener {

    @Autowired
    private TodoService todoService;

    @RabbitListener(id = "${rabbitMQ.Queue.Todos}", queues = "${rabbitMQ.Queue.Todos}")
    public void listener(Todo todo) {
        try {
            todoService.createTodo(todo);
        } catch (Exception e) {
            log.error("[Exception in TodoListener] with message {}", e.getMessage());
            log.debug("[TodoListener] exception cause:" + e);
            throw new TodoProcessingException("[TodoListener] fails by process todo");
        }
    }
}
