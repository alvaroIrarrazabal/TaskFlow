package com.taskflow.backend.task;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {


    @Mock
    private TaskMapper taskMapper;

    @Mock
    private TaskRepository taskRepository;


    @InjectMocks
    private TaskService taskService;


@Test
    void shouldFindTaskById(){

    Task task = new Task(
            "Aprender testing",
            "practicar Junit y Mockito",
            TaskPriority.HIGH,
            LocalDate.of(2026,9,30)
    );

    TaskResponse expectedResponse = new TaskResponse(

            1L,
            "Aprender testing",
            "practicar Junit y Mockito",
            TaskStatus.TODO,
            TaskPriority.HIGH,
            LocalDate.of(2026,9,30),
            LocalDateTime.of(2026, 9, 21, 12, 0),
            null

    );

    when(taskRepository.findById(1L))
            .thenReturn(Optional.of(task));

    when(taskMapper.toResponse(task)).thenReturn(expectedResponse);


    //ACT

    TaskResponse result =  taskService.findById(1L);

    //ASSERT

    assertEquals(expectedResponse, result);

    verify(taskRepository).findById(1L);
    verify(taskMapper).toResponse(task);






}


@Test
    void shouldThrowExpectionTaskDoesNotExist(){

    //ARRANGE

    when(taskRepository.findById(1111111L))
            .thenReturn(Optional.empty());


    //ACT +ASSERT

    assertThrows(
            TaskNotFoundException.class,
            () -> taskService.findById(1111111L)
    );

    //VERIFY

    verify(taskRepository).findById(1111111L);

    verify(taskMapper, never()).toResponse(any());




}


    @Test
    void shouldSortTasksByDueDateAscending() {

        // ARRANGE
        when(taskRepository.findAll(
                any(Specification.class),
                any(Sort.class)
        )).thenReturn(List.of());

        // ACT
        taskService.findAll(
                null,
                null,
                null,
                "dueDate",
                "ASC"
        );

        // ASSERT / VERIFY
        ArgumentCaptor<Sort> sortCaptor =
                ArgumentCaptor.forClass(Sort.class);

        verify(taskRepository).findAll(
                any(Specification.class),
                sortCaptor.capture()
        );

        Sort capturedSort = sortCaptor.getValue();

        Sort.Order order = capturedSort.getOrderFor("dueDate");

        assertEquals(Sort.Direction.ASC, order.getDirection());
    }



}
