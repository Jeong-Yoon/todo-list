package my.examples.todolist.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.todolist.domain.Task;
import my.examples.todolist.dto.TaskDTO;
import my.examples.todolist.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TodoListApiController {
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity todo(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Task task = modelMapper.map(taskDTO, Task.class);
        taskService.addTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTaskById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTodo(@PathVariable Long id, @RequestBody TaskDTO taskDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (taskService.getTaskById(id) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setId(id);
        taskService.updateTask(task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id){
        if (taskService.getTaskById(id) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
