package priv.wangcheng.homework.calculator;


import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

/**
 * @author WangCheng
 * @version $ Id: CalculateExpression.java, v0.1 2020/2/18 13:25 WangCheng Exp $$
 */
public class CalculateExpression implements Expression {


    /**
     * 字符串表达式
     */
    private String expression;

    /**
     * 左表达式
     */
    private Expression leftExpression;

    /**
     * 运算符
     */
    private Operator operator;

    /**
     * 右表达式
     */
    private Expression rightExpression;


    public CalculateExpression(String expression) {
        this.expression = expression;
        analysisExpression(expression);
    }

    private CalculateExpression(Expression leftExpression, Operator operator, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.operator = operator;
        this.rightExpression = rightExpression;
        this.expression = leftExpression.toString() + operator.toString() + rightExpression.toString();
    }

    @Override
    public Integer getValue() {
        Integer value;
        switch (operator.getOpt()) {
            case '+':
                value = leftExpression.getValue() + rightExpression.getValue();
                break;
            case '-':
                value = leftExpression.getValue() - rightExpression.getValue();
                break;
            case '*':
                value = leftExpression.getValue() * rightExpression.getValue();
                break;
            case '/':
                value = leftExpression.getValue() / rightExpression.getValue();
                break;
            default:
                throw new IllegalStateException("not support operate : " + operator.getOpt());
        }
        return value;
    }


    /**
     * 解析表达式
     *
     * @param expression example : 3*0+3+8+9*1 输出20   3+（3-0）*2 输出 9
     * @return
     */
    public void analysisExpression(String expression) {

        if (StringUtils.isBlank(expression)) {
            throw new IllegalArgumentException("expression is null");
        }
        char[] chars = expression.toCharArray();
        // 保存操作符
        Stack<Operator> operatorStack = new Stack<>();
        // 保存数字
        Stack<Expression> numStack = new Stack<>();

        doAnalysis(chars, operatorStack, numStack);

    }

    /**
     * 执行解析
     *
     * @param arr
     * @param operatorStack
     * @param expressionStack
     */
    private void doAnalysis(char[] arr, Stack<Operator> operatorStack, Stack<Expression> expressionStack) {
        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (current == '(') {
                operatorStack.push(Operator.valueOf(current));
                continue;
            }
            if (current == ')') {
                // 括号中的内容先 merge
                mergeExpression(operatorStack, expressionStack);
                continue;
            }
            // 如果是数字
            if (NumExpression.isNum(current)) {
                expressionStack.push(NumExpression.valueOf(String.valueOf(current)));
            } else {
                // 如果是运算符
                operatorStack.push(Operator.valueOf(current));
            }
        }
        mergeExpression(operatorStack, expressionStack);
        CalculateExpression expression = (CalculateExpression) expressionStack.pop();
        this.rightExpression = expression.rightExpression;
        this.leftExpression = expression.leftExpression;
        this.operator = expression.operator;
    }

    /**
     * 合并表达式
     *
     * @param operatorStack
     * @param expressionStack
     */
    private void mergeExpression(Stack<Operator> operatorStack, Stack<Expression> expressionStack) {

        while (!operatorStack.isEmpty()) {
            Expression expression1 = expressionStack.pop();
            Expression expression2 = expressionStack.pop();
            Operator operator = operatorStack.pop();
            //判断当前操作 和 下一次操作的优先级
            while (!operatorStack.isEmpty() && operatorStack.peek().getOrder() > operator.getOrder()) {
                Operator orderOpt = operatorStack.pop();
                Expression seqExpression = expressionStack.pop();
                expression2 = new CalculateExpression(seqExpression, orderOpt, expression2);
            }
            CalculateExpression expression = new CalculateExpression(expression2, operator, expression1);
            expressionStack.push(expression);
            if (!operatorStack.empty() && operatorStack.peek().equals(Operator.LEFT_BRACKET)) {
                operatorStack.pop();
                return;
            }
        }
    }


    public String getExpression() {
        return expression;
    }


    public Operator getOperator() {
        return operator;
    }


    @Override
    public String toString() {
        return leftExpression.toString() + operator.toString() + rightExpression.toString();
    }
}
