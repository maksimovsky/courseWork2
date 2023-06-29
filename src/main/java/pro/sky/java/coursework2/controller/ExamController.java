package pro.sky.java.coursework2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.coursework2.model.Question;
import pro.sky.java.coursework2.service.ExaminerService;

import java.util.Collection;

@RequestMapping("/exam/java/random")
@RestController
public class ExamController {
    ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Question> getQuestions(@RequestParam int amount) {
        return service.getQuestions(amount);
    }
}
