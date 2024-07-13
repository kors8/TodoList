package com.example.todo_application.repozitories;

import com.example.todo_application.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
