package com.taskflow.backend.task;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TaskService {


    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    public List<TaskResponse> findAllTasks() {


       return taskRepository.findAll()
               .stream()
               .map(taskMapper::toResponse)
               .toList();
    }




    public TaskResponse create(CreateTaskRequest request){

        Task task = taskMapper.toEntity(request);
        task.setStatus(TaskStatus.TODO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);

    }

    public TaskResponse findById(Long id) {
        Task task = findEntityById(id);
        return taskMapper.toResponse(task);
    }


    @Transactional
    public void delete(Long id) {

        System.out.println(">>> INICIO DELETE: " + id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        System.out.println(">>> TASK ENCONTRADA: " + task.getId());

        taskRepository.delete(task);

        taskRepository.flush();

        System.out.println(">>> DELETE EJECUTADO");
    }



   public TaskResponse update(Long id, UpdateTaskRequest request){

       Task task = findEntityById(id);

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDueDate(request.dueDate());



       Task updatedTask = taskRepository.save(task);
       return taskMapper.toResponse(updatedTask);


   }




   private Task findEntityById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
   }


}
