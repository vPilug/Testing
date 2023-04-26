import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testing.CalculatorTool;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {
    @ParameterizedTest
    @ValueSource(strings = {" 10 cm + 1 m - 10 mm ", " 5 cm - 20 m + 2 km - 10 cm ", " 5 m + 6 m "})
    public void calculator_works(String expression) {
        CalculatorTool calculatorTool = new CalculatorTool(expression);
        String expected = " ";
        if (expression.equals(" 10 cm + 1 m - 10 mm ")) {
            expected = " 10 cm + 1 m - 10 mm = 1090 mm";
        } else if (expression.equals(" 5 cm - 20 m + 2 km - 10 cm ")) {
            expected = " 5 cm - 20 m + 2 km - 10 cm = 197995 cm";
        } else {
            expected = " 5 m + 6 m = 11 m";
        }
        assertEquals(expected, calculatorTool.calculate());
    }

    @Test
    public void calculator_works_test1() {
        CalculatorTool calculatorToolTest1 = new CalculatorTool(" 10 cm + 1 m - 10 mm ");
        Assertions.assertEquals(" 10 cm + 1 m - 10 mm = 1090 mm", calculatorToolTest1.calculate());
    }

    @Test
    public void calculator_works_test2() {
        CalculatorTool calculatorToolTest2 = new CalculatorTool(" 5 cm - 20 m + 2 km - 10 cm ");
        Assertions.assertEquals(" 5 cm - 20 m + 2 km - 10 cm = 197995 cm", calculatorToolTest2.calculate());
    }

    @Test
    public void calculator_works_test3() {
        CalculatorTool calculatorToolTest3 = new CalculatorTool(" 5 m + 6 m ");
        Assertions.assertEquals(" 5 m + 6 m = 11 m", calculatorToolTest3.calculate());
    }
}

