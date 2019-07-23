package my.examples.todolist.service;

import lombok.RequiredArgsConstructor;
import my.examples.todolist.domain.Priority;
import my.examples.todolist.repository.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Priority> getPriorityList() {
        return priorityRepository.findAll();
    }
}
