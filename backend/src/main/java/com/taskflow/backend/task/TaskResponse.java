package com.taskflow.backend.task;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(

        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt


) {
}
