package priv.wangcheng.homework.calculator2;


/**
 * @author WangCheng
 * @version $ Id: NumExpression.java, v0.1 2020/2/18 18:03 WangCheng Exp $$
 */
public class NumExpression implements Expression {

    /**
     * 该数字在表达式中的位置
     */
    private Integer index;

    /**
     * 该数字的数值
     */
    private Integer number;

    public NumExpression(String number, Integer index) {
        this.number = Integer.valueOf(number);
        this.index = index;
    }

    public static NumExpression valueOf(String number, Integer index) {
        return new NumExpression(number, index);
    }

    /**
     * 是否是一个数字analysis
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
        return String.valueOf(number);
    }

    @Override
    public Integer getValue() {
        return number;
    }

    @Override
    public Integer getStartIndex() {
        return index;
    }

    @Override
    public Integer getEndIndex() {
        return index;
    }

    @Override
    public Integer getIndex() {
        return index;
    }
}
