package my.examples.todolist.service;

import lombok.RequiredArgsConstructor;
import my.examples.todolist.domain.Task;
import my.examples.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.delete(taskRepository.getOne(id));
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
