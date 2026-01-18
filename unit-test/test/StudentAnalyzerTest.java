import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    @Test
    void testCountExcellentStudents_NormalCase_Mixed() {
        List<Double> scores = Arrays.asList(9.5, 8.0, 7.9, 10.0, 11.0, -0.5, 8.7, 9.0);
        assertEquals(5, analyzer.countExcellentStudents(scores));
    }

    @Test
    void testCountExcellentStudents_AllExcellent() {
        List<Double> scores = Arrays.asList(8.0, 9.0, 10.0, 8.5);
        assertEquals(4, analyzer.countExcellentStudents(scores));
    }

    @Test
    void testCountExcellentStudents_EmptyList() {
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
    }

    @Test
    void testCountExcellentStudents_NullList() {
        assertEquals(0, analyzer.countExcellentStudents(null));
    }

    @Test
    void testCountExcellentStudents_NoExcellentButValidScores() {
        List<Double> scores = Arrays.asList(7.9, 6.5, 0.0, 7.0);
        assertEquals(0, analyzer.countExcellentStudents(scores));
    }

    @Test
    void testCountExcellentStudents_OnlyBoundaryValues() {
        List<Double> scores = Arrays.asList(8.0, 7.999, 10.0, 8.0001);
        assertEquals(3, analyzer.countExcellentStudents(scores));
    }

    @Test
    void testCalculateValidAverage_NormalCase_Mixed() {
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0, 10.0);
        assertEquals(8.625, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    void testCalculateValidAverage_AllValid() {
        List<Double> scores = Arrays.asList(5.5, 6.0, 8.5);
        assertEquals(6.667, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    void testCalculateValidAverage_EmptyList() {
        assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), 0.001);
    }

    @Test
    void testCalculateValidAverage_NullList() {
        assertEquals(0.0, analyzer.calculateValidAverage(null), 0.001);
    }

    @Test
    void testCalculateValidAverage_NoValidScores() {
        List<Double> scores = Arrays.asList(-1.0, 11.0, -5.5, 12.3);
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    void testCalculateValidAverage_OnlyZeroAndTen() {
        List<Double> scores = Arrays.asList(0.0, 10.0, 0.0, 10.0);
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    void testCalculateValidAverage_SingleValidScore() {
        List<Double> scores = Arrays.asList(7.5, -1.0, 12.0);
        assertEquals(7.5, analyzer.calculateValidAverage(scores), 0.001);
    }
}
