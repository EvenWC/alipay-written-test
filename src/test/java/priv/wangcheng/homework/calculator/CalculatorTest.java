package priv.wangcheng.homework.calculator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author WangCheng
 * @version $ Id: CalculatorTest.java, v0.1 2020/2/18 21:53 WangCheng Exp $$
 */
public class CalculatorTest {

    @Test
    public void test_1() {
        String expression = "3*0+3+8+9*1";
        Integer calculate = calculate(expression);
        Assert.assertEquals(Integer.valueOf(20), calculate);
    }


    @Test
    public void test_2() {
        String expression = "3+(3-0)*2";
        Integer calculate = calculate(expression);
        Assert.assertEquals(Integer.valueOf(9), calculate);
    }

    @Test
    public void test_3() {
        String expression = "3*2*2+1";
        Integer calculate = calculate(expression);
        Assert.assertEquals(Integer.valueOf(13), calculate);
    }

    private Integer calculate(String expression) {
        CalculateExpression calculateExpression = new CalculateExpression(expression);

        Integer value = calculateExpression.getValue();

        return value;
    }
}
