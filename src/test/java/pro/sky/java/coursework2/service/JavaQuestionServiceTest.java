package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.Test;
import pro.sky.java.coursework2.exception.QuestionAlreadyAddedException;
import pro.sky.java.coursework2.exception.QuestionNotFoundException;
import pro.sky.java.coursework2.model.Question;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    JavaQuestionService out = new JavaQuestionService();

    @Test
    void add() {
        Question expected = new Question("question", "answer");
        assertEquals(expected, out.add("question", "answer"));
        assertThrows(QuestionAlreadyAddedException.class, () -> out.add("question", "answer"));
        assertThrows(IllegalArgumentException.class, () -> out.add(null, "question"));
        assertThrows(IllegalArgumentException.class, () -> out.add("question", null));

        out.remove(expected);
    }

    @Test
    void testAdd() {
        Question expected = new Question("question", "answer");
        assertEquals(expected, out.add(expected));
        assertThrows(QuestionAlreadyAddedException.class, () -> out.add(expected));

        out.remove(expected);
    }

    @Test
    void remove() {
        Question expected = new Question("question", "answer");
        out.add(expected);
        assertEquals(expected, out.remove(expected));
        assertThrows(QuestionNotFoundException.class, () -> out.remove(expected));
    }

    @Test
    void getAll() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Collection<Question> expected = List.of(question1, question2, question3);
        out.add(question1);
        out.add(question2);
        out.add(question3);
        assertEquals(expected, out.getAll());
    }
}