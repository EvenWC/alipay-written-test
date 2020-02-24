package priv.wangcheng.homework.calculator;

/**
 * @author WangCheng
 * @version $ Id: NumExpression.java, v0.1 2020/2/18 18:03 WangCheng Exp $$
 */
public class NumExpression implements Expression {

    private Integer number;

    public NumExpression(String number) {
        this.number = Integer.valueOf(number);
    }

    public static NumExpression valueOf(String number) {
        return new NumExpression(number);
    }

    /**
     * 是否是一个数字
     *
     * @param number
     * @return
     */
    public static Boolean isNum(char number) {
        return number >= '0' && number <= '9';
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + "";
    }

    @Override
    public Integer getValue() {
        return number;
    }
}
