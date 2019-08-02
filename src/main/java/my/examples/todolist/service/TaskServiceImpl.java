package my.examples.todolist.service;

import lombok.RequiredArgsConstructor;
import my.examples.todolist.domain.Task;
import my.examples.todolist.dto.TaskDTO;
import my.examples.todolist.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Task> getTasks(int page) {
    	Sort sort = Sort.by(Sort.Order.desc("regDate"));
    	Pageable pageable = PageRequest.of(page, 5, sort);
        return taskRepository.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    @Transactional
    public void addTask(TaskDTO taskDTO) {
    	Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setContent(taskDTO.getContent());
        task.setEndDate(taskDTO.getEndDate());
        task.setPriority(taskDTO.getPriority());
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.delete(taskRepository.getOne(id));
    }

    @Override
    @Transactional
    public void updateTask(Long id, TaskDTO taskDTO){
        Task task = new Task();
        task.setId(id);
        task.setTitle(taskDTO.getTitle());
        task.setContent(taskDTO.getContent());
        task.setEndDate(taskDTO.getEndDate());
        task.setPriority(taskDTO.getPriority());
        taskRepository.save(task);
    }
}
