package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.coursework2.exception.NoSuchNumbersOfQuestionsException;
import pro.sky.java.coursework2.model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionServiceMock;
    @Mock
    private MathQuestionService mathQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl out;

    @BeforeEach
    void before() {
        out = new ExaminerServiceImpl(javaQuestionServiceMock, mathQuestionServiceMock);
    }

    @Test
    void getJavaQuestions() {
        Question question1 = new Question("javaQuestion1", "javaAnswer1");
        Question question2 = new Question("javaQuestion2", "javaAnswer2");
        Question question3 = new Question("javaQuestion3", "javaAnswer3");
        Question question4 = new Question("javaQuestion4", "javaAnswer4");
        Question question5 = new Question("javaQuestion5", "javaAnswer5");
        Collection<Question> expected = List.of(question1, question2, question3, question4, question5);
        when(javaQuestionServiceMock.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3)
                .thenReturn(question4)
                .thenReturn(question5);
        when(javaQuestionServiceMock.getAll()).thenReturn(expected);
        assertEquals(expected, out.getJavaQuestions(5));

        expected = List.of(question1, question2, question3);
        when(javaQuestionServiceMock.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);
        assertEquals(expected, out.getJavaQuestions(3));

        assertThrows(NoSuchNumbersOfQuestionsException.class, () -> out.getJavaQuestions(6));
        assertThrows(NoSuchNumbersOfQuestionsException.class, () -> out.getJavaQuestions(0));
    }

    @Test
    void getMathQuestions() {
        Question question1 = new Question("mathQuestion1", "mathAnswer1");
        Question question2 = new Question("mathQuestion2", "mathAnswer2");
        Question question3 = new Question("mathQuestion3", "mathAnswer3");
        Question question4 = new Question("mathQuestion4", "mathAnswer4");
        Question question5 = new Question("mathQuestion5", "mathAnswer5");
        Collection<Question> expected = List.of(question1, question2, question3, question4, question5);
        when(mathQuestionServiceMock.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3)
                .thenReturn(question4)
                .thenReturn(question5);
        when(mathQuestionServiceMock.getAll()).thenReturn(expected);
        assertEquals(expected, out.getMathQuestions(5));

        expected = List.of(question1, question2, question3);
        when(mathQuestionServiceMock.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);
        assertEquals(expected, out.getMathQuestions(3));

        assertThrows(NoSuchNumbersOfQuestionsException.class, () -> out.getMathQuestions(6));
        assertThrows(NoSuchNumbersOfQuestionsException.class, () -> out.getMathQuestions(0));
    }

    @Test
    void getJavaAndMathQuestions() {
        Question question1 = new Question("javaQuestion1", "javaAnswer1");
        Question question2 = new Question("javaQuestion2", "javaAnswer2");
        Question question3 = new Question("javaQuestion3", "javaAnswer3");
        List<Question> expected = List.of(question1, question2, question3);
        when(javaQuestionServiceMock.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);
        when(javaQuestionServiceMock.getAll()).thenReturn(expected);
        assertEquals(expected, out.getJavaAndMathQuestions(3));

        question1 = new Question("mathQuestion1", "mathAnswer1");
        question2 = new Question("mathQuestion2", "mathAnswer2");
        question3 = new Question("mathQuestion3", "mathAnswer3");
        expected = List.of(question1, question2, question3);
        when(mathQuestionServiceMock.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);
        when(mathQuestionServiceMock.getAll()).thenReturn(expected);
        when(javaQuestionServiceMock.getAll()).thenReturn(new ArrayList<>());
        assertEquals(expected, out.getJavaAndMathQuestions(3));
    }
}