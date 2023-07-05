package pro.sky.java.coursework2.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.coursework2.exception.QuestionAlreadyAddedException;
import pro.sky.java.coursework2.exception.QuestionNotFoundException;
import pro.sky.java.coursework2.model.Question;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Repository
public class MathQuestionRepository implements QuestionRepository {
    private static final List<Question> questions = new ArrayList<>();

    @PostConstruct
    private void init() {
        add(new Question("Что нужно найти в первую очередь, решая квадратное уравнение? ",
                "Дискриминант"));
        add(new Question("Чему симметричен график нечетной функции?", "Началу координат"));
        add(new Question("На озере росли лилии. Каждый день их число удваивалось, и на 20-ый день " +
                "заросло все озеро. На какой день заросла половина озера?", "На 19 день"));
        add(new Question("Что не имеет длины, ширины, высоты, а можно измерить?",
                "время, температура"));
        add(new Question("Какова вероятность того, что при трех бросаниях монеты ни разу не " +
                "выпадет «орёл»?", "0,125"));
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
}
