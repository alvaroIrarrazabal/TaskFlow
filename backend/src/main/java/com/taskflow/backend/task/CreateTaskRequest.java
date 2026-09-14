package com.taskflow.backend.task;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskRequest(

        @NotBlank(message = "Title is required")
        @Size(max = 120, message = "Title must not exceed 120 characters")
        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String title,
        @Size(max = 1000)
        String description,
        TaskPriority priority,
        LocalDate dueDate
) {
}
