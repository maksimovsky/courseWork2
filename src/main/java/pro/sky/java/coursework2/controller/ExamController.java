package pro.sky.java.coursework2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.coursework2.model.Question;
import pro.sky.java.coursework2.service.ExaminerService;

import java.util.Collection;

@RequestMapping("/exam/")
@RestController
public class ExamController {
    ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping("/java/get")
    public Collection<Question> getJavaQuestions(@RequestParam int amount) {
        return service.getJavaQuestions(amount);
    }

    @GetMapping("/math/get")
    public Collection<Question> getMathQuestions(@RequestParam int amount) {
        return service.getMathQuestions(amount);
    }

    @GetMapping("/get")
    public Collection<Question> getJavaAndMathQuestions(@RequestParam int amount) {
        return service.getJavaAndMathQuestions(amount);
    }
}
