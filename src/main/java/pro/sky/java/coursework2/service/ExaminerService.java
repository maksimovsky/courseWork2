package pro.sky.java.coursework2.service;

import pro.sky.java.coursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getJavaQuestions(int amount);

    Collection<Question> getMathQuestions(int amount);
    Collection<Question> getJavaAndMathQuestions(int amount);
}
