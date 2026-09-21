package com.taskflow.backend.task;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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



    //buscadores


    public List<TaskResponse> findAll(
            TaskStatus status,
            TaskPriority priority,
            String search,
            String sortBy,
            String direction) {

        Specification<Task> specification =
                buildSpecification(status, priority, search);

        Sort.Direction sortDirection =
                resolveSortDirection(direction);

        List<Task> tasks;

        if ("priority".equals(sortBy)) {

            specification = specification.and(
                    TaskSpecification.orderByPriority(sortDirection)
            );

            tasks = taskRepository.findAll(specification);

        } else {

            Sort sort = buildSort(sortBy, direction);

            tasks = taskRepository.findAll(specification, sort);
        }

        return tasks.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    private Specification<Task> buildSpecification(
            TaskStatus status,
            TaskPriority priority,
            String search){

        Specification<Task> specification = Specification.unrestricted();

        if(status != null) {
            specification = specification.and(TaskSpecification.hasStatus(status)
            );
        }
        if(priority != null) {
            specification = specification.and(TaskSpecification.hasPriority(priority));
        }
        if (search != null && !search.isEmpty()) {
            specification = specification.and (TaskSpecification.titleContains(search));
        }
        return specification;

    }



    private Sort buildSort(String sortBy, String direction){

        List<String> allowedSortFields = List.of(
                "dueDate",
                "priority"
        );

        String sortFIeld =
                sortBy != null && allowedSortFields.contains(sortBy)
                        ? sortBy
                        : "dueDate";

        String sortDirectionValue =
                "DESC".equalsIgnoreCase(direction)
                        ? "DESC"
                        : "ASC";

        Sort.Direction sortDirection =
                Sort.Direction.fromString(sortDirectionValue);

        Sort sort = Sort.by(
                sortDirection,
                sortFIeld
        );
        return Sort.by(sortDirection, sortFIeld);
    }

    private Sort.Direction resolveSortDirection(String direction) {
        return "DESC".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
    }


}
