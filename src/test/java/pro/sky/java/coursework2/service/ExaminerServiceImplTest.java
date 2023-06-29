package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.coursework2.exception.NoSuchNumbersOfQuestionsException;
import pro.sky.java.coursework2.model.Question;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getRandom() {
        Question question = new Question("question", "answer");
        Collection<Question> expected = List.of(question);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(question);
        when(javaQuestionServiceMock.getAll()).thenReturn(expected);
        assertEquals(expected, out.getQuestions(1));

        assertThrows(NoSuchNumbersOfQuestionsException.class, () -> out.getQuestions(2));
        assertThrows(NoSuchNumbersOfQuestionsException.class, () -> out.getQuestions(0));
    }
}