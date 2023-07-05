package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.coursework2.exception.QuestionAlreadyAddedException;
import pro.sky.java.coursework2.exception.QuestionNotFoundException;
import pro.sky.java.coursework2.model.Question;
import pro.sky.java.coursework2.repository.JavaQuestionRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private JavaQuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    private JavaQuestionService out;

    @Test
    void add() {
        Question expected = new Question("question", "answer");
        when(javaQuestionRepositoryMock.add(eq(expected)))
                .thenReturn(expected)
                .thenThrow(QuestionAlreadyAddedException.class);
        assertEquals(expected, out.add("question", "answer"));
        assertThrows(QuestionAlreadyAddedException.class, () -> out.add("question", "answer"));

        out.remove(expected);
    }

    @Test
    void testAdd() {
        Question expected = new Question("question", "answer");
        when(javaQuestionRepositoryMock.add(eq(expected)))
                .thenReturn(expected)
                .thenThrow(QuestionAlreadyAddedException.class);
        assertEquals(expected, out.add(expected));
        assertThrows(QuestionAlreadyAddedException.class, () -> out.add(expected));

        Question question = new Question("question", "answer");
        assertThrows(QuestionAlreadyAddedException.class, () -> out.add(question));

        out.remove(expected);
    }

    @Test
    void remove() {
        Question expected = new Question("question", "answer");
        when(javaQuestionRepositoryMock.add(eq(expected))).thenReturn(expected);
        when(javaQuestionRepositoryMock.remove(eq(expected)))
                .thenReturn(expected)
                .thenThrow(QuestionNotFoundException.class)
                .thenThrow(QuestionNotFoundException.class);
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
        when(javaQuestionRepositoryMock.add(any()))
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);
        out.add(question1);
        out.add(question2);
        out.add(question3);
        when(javaQuestionRepositoryMock.getAll()).thenReturn(expected);
        assertEquals(expected, out.getAll());
    }
}