package pro.sky.java.coursework2.repository;

import pro.sky.java.coursework2.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
