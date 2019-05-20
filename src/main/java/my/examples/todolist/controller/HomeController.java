package my.examples.todolist.controller;

import lombok.RequiredArgsConstructor;
import my.examples.todolist.service.PriorityService;
import my.examples.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final TaskService taskService;
    private final PriorityService priorityService;

    @GetMapping
    public String home(Model model){
        model.addAttribute("taskList", taskService.getTaskList());
        model.addAttribute("priorities",priorityService.getPriorityList());
        return "index";
    }

    @GetMapping("/addTask")
    public String addTodo(Model model){
        model.addAttribute("priorities",priorityService.getPriorityList());
        return "addTask";
    }

    @GetMapping("/modify")
    public String modify(){
        return "modify";
    }

}
