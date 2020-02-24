package priv.wangcheng.homework.calculator;

import java.util.Objects;

/**
 * @author WangCheng
 * @version $ Id: Operator.java, v0.1 2020/2/18 13:25 WangCheng Exp $$
 */
public class Operator {

    public static final Operator ADD = new Operator(0, '+');

    public static final Operator SUB = new Operator(0, '-');

    public static final Operator MULTI = new Operator(1, '*');

    public static final Operator DIV = new Operator(1, '/');

    public static final Operator LEFT_BRACKET = new Operator(-1, '(');

    public static final Operator RIGHT_BRACKET = new Operator(-1, ')');

    /**
     * 优先级
     */
    private int order;
    /**
     * 操作符
     */
    private char opt;

    private Operator(int order, char opt) {
        this.order = order;
        this.opt = opt;
    }

    public static Operator valueOf(Character opt) {
        if (Objects.isNull(opt)) {
            throw new IllegalArgumentException("operator is null");
        }
        Operator operator;
        switch (opt) {
            case '+':
                operator = ADD;
                break;
            case '-':
                operator = SUB;
                break;
            case '*':
                operator = MULTI;
                break;
            case '/':
                operator = DIV;
                break;
            case '(':
                operator = LEFT_BRACKET;
                break;
            case ')':
                operator = RIGHT_BRACKET;
                break;
            default:
                operator = null;
                break;
        }
        if (Objects.isNull(operator)) {
            throw new IllegalArgumentException(String.format("The operator 【%s】 is not supported", opt));
        }
        return operator;
    }

    public int getOrder() {
        return order;
    }

    public char getOpt() {
        return opt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operator operator = (Operator) o;
        return order == operator.order &&
            opt == operator.opt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, opt);
    }

    @Override
    public String toString() {
        return opt + "";
    }
}