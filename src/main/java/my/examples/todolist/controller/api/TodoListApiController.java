package my.examples.todolist.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.todolist.domain.Task;
import my.examples.todolist.dto.TaskDTO;
import my.examples.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TodoListApiController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity addTodo(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        taskService.addTask(taskDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTaskById(id),HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<Page<Task>> getTasks(@RequestParam(name = "page", required = false, defaultValue = "1") int page){
        if(page < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Page<Task> tasks = taskService.getTasks(page - 1);
        if(tasks.getTotalPages() < page)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTodo(@PathVariable Long id, @RequestBody TaskDTO taskDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (taskService.getTaskById(id) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        taskService.updateTask(id,taskDTO);
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
