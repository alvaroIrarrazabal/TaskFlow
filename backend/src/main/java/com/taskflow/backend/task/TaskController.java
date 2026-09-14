    package com.taskflow.backend.task;

    import jakarta.validation.Valid;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/tasks")
    public class TaskController {



        private final TaskService taskService;

        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }


        @GetMapping
        public List<TaskResponse> findAll(){

            return taskService.findAllTasks();
        }

        @GetMapping("/{id}")
        public TaskResponse findOne(@PathVariable Long id){
            return taskService.findById(id);
        }

        @PostMapping
        public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request){

           TaskResponse response = taskService.create(request);


           return  ResponseEntity.status(HttpStatus.CREATED).body(response);

        }

        @PutMapping("/{id}")
        public TaskResponse update( @PathVariable Long id,@Valid @RequestBody UpdateTaskRequest request){

                return taskService.update(id, request);
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {

            taskService.delete(id);

            return ResponseEntity.noContent().build();
        }
    }
