package pro.sky.java.coursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.java.coursework2.exception.NoSuchNumbersOfQuestionsException;
import pro.sky.java.coursework2.model.Question;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    QuestionService questionService;

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount || amount < 1) {
            throw new NoSuchNumbersOfQuestionsException();
        }
        Collection<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Question question = questionService.getRandomQuestion();
            if (questions.contains(question)) {
                i--;
                continue;
            }
            questions.add(question);
        }
        return questions;
    }
}
