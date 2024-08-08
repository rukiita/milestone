package com.example.milestone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    private TaskDao dao = new TaskDao();

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = this.dao.findAll();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/tasks/add")
    public String getAddTask(Model model) {
        model.addAttribute("input", new PostTaskRequest());
        return "task-add";
    }

    @PostMapping("/tasks/add")
    public String postAddTask(@ModelAttribute PostTaskRequest input) {
        Task task = new Task(input.getTitle(), input.getAuthor(), TaskClassification.valueOf(input.getClassification()), convertStringToDate(input.getDueDate()));
        this.dao.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskDetail(@PathVariable String id, Model model) {
        Task task = this.dao.findTaskById(id);
        model.addAttribute("task", task);
        System.out.println(0);
        return "task-detail";
    }

    @GetMapping("/tasks/{id}/delete")
    public String getTaskDelete(@PathVariable String id) {
        this.dao.deleteTaskById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String getEditTask(@PathVariable String id, Model model) {
        Task task = this.dao.findTaskById(id);
        if (task == null) {
            return "redirect:/tasks";
        }
        PostTaskRequest input = createInputFromTask(task);
        model.addAttribute("input", input);
        model.addAttribute("id", id);
        return "task-edit";
    }

    @PostMapping("/tasks/{id}/edit")
    public String postEditTask(@PathVariable String id, @ModelAttribute PostTaskRequest input) {
        System.out.println("Received input: " + input);
        Task task = applyInputToTask(id, input);
        System.out.println("Task to update: " + task);
        if (task == null) {
            return "redirect:/tasks";
        }
        this.dao.updateTask(task);
        return "redirect:/tasks/" + id;
    }

    private Task applyInputToTask(String id, PostTaskRequest input) {
        Task task = this.dao.findTaskById(id);
        if (task == null) {
            return null;
        }
        task.setTitle(input.getTitle());
        task.setAuthor(input.getAuthor());
        task.setClassification(TaskClassification.valueOf(input.getClassification()));
        task.setDueDate(convertStringToDate(input.getDueDate()));
        return task;
    }

    private PostTaskRequest createInputFromTask(Task task) {
        PostTaskRequest input = new PostTaskRequest();
        input.setTitle(task.getTitle());
        input.setAuthor(task.getAuthor());
        input.setClassification(task.getClassification().name());
        input.setDueDate(new SimpleDateFormat("yyyy-MM-dd").format(task.getDueDate()));
        return input;
    }

    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}