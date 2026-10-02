import { Component, inject, OnInit } from '@angular/core';
import { NgClass, DatePipe } from '@angular/common';
import {
  CreateTaskRequest,
  Task,
  TaskPriority,
  TaskStatus,
  UpdateTaskRequest,
} from '../models/task.model';
import { TaskService } from '../service/task.service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [ReactiveFormsModule, NgClass, DatePipe],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.css',
})
export class TasksComponent implements OnInit {
  private readonly formBuilder = inject(FormBuilder);

  tasks: Task[] = [];
  errorMessage = '';
  createMessage = '';
  createError = '';
  deleteMessage = '';
  loading = false;
  creating = false;
  selectedStatus: TaskStatus | null = null;
  selectedPriority: TaskPriority | null = null;
  selectedSortBy = 'dueDate';
  selectedDirection = 'ASC';
  searchControl = this.formBuilder.nonNullable.control('');
  editingTaskId: number | null = null;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  taskForm = this.formBuilder.group({
    title: this.formBuilder.nonNullable.control('', [
      Validators.required,
      Validators.maxLength(120),
    ]),
    description: this.formBuilder.nonNullable.control('', [
      Validators.required,
      Validators.maxLength(1000),
    ]),
    status: this.formBuilder.nonNullable.control<TaskStatus>('TODO'),
    priority: this.formBuilder.nonNullable.control<TaskPriority>('MEDIUM'),
    dueDate: this.formBuilder.nonNullable.control('', [Validators.required]),
  });

  //crearTarea

  onSubmit(): void {
    if (this.taskForm.invalid) {
      return;
    }
    if (this.editingTaskId !== null) {
      this.updateTask();
      return;
    }
    this.createTask();
  }

  //eliminar tarea

  deleteTask(id: number): void {
      const confirmed = confirm(
        '¿Estás seguro de que deseas eliminar esta tarea?',
      );

      if (!confirmed) {
        return;
      }
    this.deleteMessage = '';
    this.errorMessage = '';
    this.createError = '';

    this.taskService.deleteTask(id).subscribe({
      next: () => {
        this.tasks = this.tasks.filter((task) => task.id !== id);
        this.deleteMessage = 'Tarea eliminada correctamente.';

        setTimeout(() => {
          this.deleteMessage = '';
        }, 2000);
      },
      error: (error) => {
        console.log('error al eliminar la tareas', error);
        this.deleteMessage = 'No se pudo eliminar la tarea.';
      },
    });
  }

  //carga los campos para editar tareas

  startEdit(task: Task): void {
    this.editingTaskId = task.id;

    this.taskForm.patchValue({
      title: task.title,
      description: task.description,
      status: task.status,
      priority: task.priority,
      dueDate: task.dueDate,
    });

     document.getElementById('task-form')?.scrollIntoView({
       behavior: 'smooth',
       block: 'start',
     });
  }

  //actualizar tarea

  updateTask(): void {
    if (this.editingTaskId === null) {
      return;
    }

    this.creating = true;
    this.createMessage = '';
    this.createError = '';

    const formValue = this.taskForm.getRawValue();

    const request: UpdateTaskRequest = {
      title: formValue.title,
      description: formValue.description,
      status: formValue.status,
      priority: formValue.priority,
      dueDate: formValue.dueDate,
    };

    this.taskService.updateTask(this.editingTaskId, request).subscribe({
      next: (updateTask) => {
        this.tasks = this.tasks.map((task) =>
          task.id === updateTask.id ? updateTask : task,
        );

        this.editingTaskId = null;

        this.taskForm.reset({
          title: '',
          description: '',
          status: 'TODO',
          priority: 'MEDIUM',
          dueDate: '',
        });

        this.createMessage = 'Tarea actualizada correctamente';
        
        setTimeout(() => {
          this.createMessage = '';
        }, 3000);
        this.creating = false;
      },

      error: (error) => {
        console.error('Error upading');

        this.createError = 'No se pudo actualizar la tarea';
        
        setTimeout(() => {
          this.createError = '';
        }, 3000);

        this.creating = false;
      },
    });
  }

  //crear tarea

  createTask(): void {
    this.creating = true;
    this.createMessage = '';
    this.createError = '';

    const request: CreateTaskRequest = {
      title: this.taskForm.value.title!,
      description: this.taskForm.value.description!,
      priority: this.taskForm.value.priority!,
      dueDate: this.taskForm.value.dueDate!,
    };

    this.taskService.createTask(request).subscribe({
      next: (createdTask) => {
        this.tasks.push(createdTask);

        this.editingTaskId = null;

        this.taskForm.reset({
          title: '',
          description: '',
          priority: 'MEDIUM',
          dueDate: '',
        });

        this.createMessage = 'tarea creada correctamente';
        setTimeout(() => {
          this.createMessage = '';
        }, 3000);
        this.creating = false;
      },

      error: (error) => {
        console.error('Error creating task:', error);

        this.createError = 'No se pudo crear la tarea';
        setTimeout(() => {
          this.createError = '';
        }, 3000);
        this.creating = false;
      },
    });
  }

  //cargar tareas
  loadTasks(): void {
    this.loading = true;
    this.errorMessage = '';
    this.taskService
      .getTasks(
        this.selectedStatus ?? undefined,
        this.selectedPriority ?? undefined,
        this.searchControl.value,
        this.selectedSortBy,
        this.selectedDirection,
      )
      .subscribe({
        next: (tasks) => {
          this.tasks = tasks;
          this.loading = false;
        },
        error: (error) => {
          console.error(error);
          this.errorMessage =
            'No se pudo cargar la lista de tareas. Por favor, inténtelo de nuevo más tarde.';
          this.loading = false;
        },
      });
  }

  //filtros de task
  //filtrar por estado

  filterByStatus(status: TaskStatus | null) {
    this.selectedStatus = status;
    this.loadTasks();
  }

  onStatusChange(event: Event): void {
    const select = event.target as HTMLSelectElement;

    const value = select.value;

    if (value === '') {
      this.filterByStatus(null);
      return;
    }
    this.filterByStatus(value as TaskStatus);
  }

  //por prioridad

  filterByPriority(priority: TaskPriority | null): void {
    this.selectedPriority = priority;
    this.loadTasks();
  }

  onPriorityChange(event: Event): void {
    const select = event.target as HTMLSelectElement;

    const value = select.value;

    if (value === '') {
      this.filterByPriority(null);
      return;
    }
    this.filterByPriority(value as TaskPriority);
  }

  searchTask(): void {
    this.loadTasks();
  }

  onBySortChange(event: Event): void {
    const select = event.target as HTMLSelectElement;
    this.selectedSortBy = select.value;
    this.loadTasks();
  }

  onDirectionChange(event: Event): void {
    const select = event.target as HTMLSelectElement;
    this.selectedDirection = select.value;
    this.loadTasks();
  }

  cancelEdit(): void {
    this.editingTaskId = null;

    this.taskForm.reset({
      title: '',
      description: '',
      status: 'TODO',
      priority: 'MEDIUM',
      dueDate: '',
    });
  }
}
