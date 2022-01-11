package com.batsandrey.sweater.repository;

import com.batsandrey.sweater.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
}
