package com.taskflow.backend.task;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> hasStatus(TaskStatus status) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> hasPriority(TaskPriority priority) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("priority"), priority);
    }

    public static Specification<Task> titleContains(String search){
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + search.toLowerCase() + "%");

    }


    public static  Specification<Task> orderByPriority(Sort.Direction direction){

        return (root, query, criteriaBuilder) ->
        {
            var priorityOrder = criteriaBuilder.selectCase(root.get("priority"))
                    .when(TaskPriority.LOW,1)
                    .when(TaskPriority.MEDIUM,2)
                    .when(TaskPriority.HIGH,3)
                    .otherwise(4);

            if(direction == Sort.Direction.ASC){
                query.orderBy(criteriaBuilder.desc(priorityOrder));
            }else{
                query.orderBy(criteriaBuilder.asc(priorityOrder));
            }

            return criteriaBuilder.conjunction();
        };
    }
}
