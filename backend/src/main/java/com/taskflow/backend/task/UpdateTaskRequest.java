package com.taskflow.backend.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateTaskRequest(

        @NotBlank
        @Size(max = 120)
        String title,

        @Size(max = 1000)
        String description,

        TaskStatus status,

        TaskPriority priority,

        LocalDate dueDate


) {
}
