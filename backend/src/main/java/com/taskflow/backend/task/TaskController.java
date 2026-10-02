    package com.taskflow.backend.task;

    import jakarta.validation.Valid;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @CrossOrigin(origins = {
            "http://localhost:4200",
            "https://taskflow-web-w9n3.onrender.com"
    })
    @RestController
    @RequestMapping("/api/tasks")
    public class TaskController {



        private final TaskService taskService;

        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }


        @GetMapping
        public ResponseEntity<List<TaskResponse>> findAll(
                @RequestParam(required = false) TaskStatus status,
                @RequestParam(required = false) TaskPriority priority,
                @RequestParam(required = false) String search,
                @RequestParam(required = false) String sortBy,
                @RequestParam(required = false) String direction
        ) {


            return ResponseEntity.ok(
                    taskService.findAll(status, priority, search,sortBy,direction)
            );
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
