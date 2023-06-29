package pro.sky.java.coursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.java.coursework2.exception.QuestionAlreadyAddedException;
import pro.sky.java.coursework2.exception.QuestionNotFoundException;
import pro.sky.java.coursework2.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private static final List<Question> questions = new ArrayList<>();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyAddedException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableList(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int num = random.nextInt(questions.size());
        return questions.get(num);
    }
}
