package my.examples.todolist.service;

import my.examples.todolist.domain.Task;
import my.examples.todolist.dto.TaskDTO;

import java.util.List;

import org.springframework.data.domain.Page;

public interface TaskService {
    public List<Task> getTaskList();
    public Page<Task> getTasks(int page);
    public Task getTaskById(Long id);
    public void addTask(TaskDTO taskDTO);
    public void deleteTask(Long id);
    public void updateTask(Long id, TaskDTO taskDTO);
}
