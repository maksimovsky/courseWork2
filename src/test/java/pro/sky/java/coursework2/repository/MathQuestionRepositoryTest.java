package pro.sky.java.coursework2.repository;

import org.junit.jupiter.api.Test;
import pro.sky.java.coursework2.exception.QuestionAlreadyAddedException;
import pro.sky.java.coursework2.exception.QuestionNotFoundException;
import pro.sky.java.coursework2.model.Question;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryTest {
    MathQuestionRepository out = new MathQuestionRepository();

    @Test
    void add() {
        Question expected = new Question("question", "answer");
        assertEquals(0, out.getAll().size());
        assertEquals(expected, out.add(expected));
        assertEquals(1, out.getAll().size());
        assertTrue(out.getAll().contains(expected));
        assertThrows(QuestionAlreadyAddedException.class, () -> out.add(expected));

        out.remove(expected);
    }

    @Test
    void remove() {
        Question expected = new Question("question", "answer");
        out.add(expected);
        assertEquals(1, out.getAll().size());
        assertEquals(expected, out.remove(expected));
        assertEquals(0, out.getAll().size());
        assertFalse(out.getAll().contains(expected));
        assertThrows(QuestionNotFoundException.class, () -> out.remove(expected));
        Question question = new Question("question2", "answer");
        assertThrows(QuestionNotFoundException.class, () -> out.remove(question));
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

        out.remove(question1);
        out.remove(question2);
        out.remove(question3);
    }
}