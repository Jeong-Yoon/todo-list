package my.examples.todolist.service;

import my.examples.todolist.domain.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getTaskList();
    public Task getTaskById(Long id);
    public void addTask(Task task);
    public void deleteTask(Long id);
    public void updateTask(Task task);
}
