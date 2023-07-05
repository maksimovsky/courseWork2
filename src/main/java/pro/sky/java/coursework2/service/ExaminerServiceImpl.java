package pro.sky.java.coursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.java.coursework2.exception.NoSuchNumbersOfQuestionsException;
import pro.sky.java.coursework2.model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getJavaQuestions(int amount) {
        return getQuestions(javaQuestionService, amount);
    }

    @Override
    public Collection<Question> getMathQuestions(int amount) {
        return getQuestions(mathQuestionService, amount);
    }

    private Collection<Question> getQuestions(QuestionService questionService, int amount) {
        if (amount > questionService.getAll().size() || amount < 1) {
            throw new NoSuchNumbersOfQuestionsException();
        }
        Collection<Question> questions = new ArrayList<>();
        Question question;
        for (int i = 0; i < amount; i++) {
            question = questionService.getRandomQuestion();
            if (questions.contains(question)) {
                i--;
                continue;
            }
            questions.add(question);
        }
        return questions;
    }

    public Collection<Question> getJavaAndMathQuestions(int amount) {
        int sumSize = javaQuestionService.getAll().size() + mathQuestionService.getAll().size();
        if (amount > sumSize || amount < 1) {
            throw new NoSuchNumbersOfQuestionsException();
        }
        Collection<Question> questions = new ArrayList<>();
        Question question;
        QuestionService questionService;
        Random random = new Random();
        for (int i = 0; i < amount;) {
            if (random.nextInt(2) == 1) {
                questionService = javaQuestionService;
            } else {
                questionService = mathQuestionService;
            }
            if (questionService.getAll().isEmpty()) {
                continue;
            }
            question = questionService.getRandomQuestion();
            if (questions.contains(question)) {
                continue;
            }
            questions.add(question);
            i++;
        }
        return questions;
    }
}
