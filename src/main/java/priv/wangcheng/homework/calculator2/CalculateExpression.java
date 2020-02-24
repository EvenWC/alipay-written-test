package priv.wangcheng.homework.calculator2;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import priv.wangcheng.homework.calculator2.calculate.CalculateElement;
import priv.wangcheng.homework.calculator2.decorate.LeftBracket;
import priv.wangcheng.homework.calculator2.decorate.RightBracket;

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
     * 左括号
     */
    private LeftBracket leftBracket;

    /**
     * 左表达式
     */
    private Expression leftExpression;

    /**
     * 运算元素
     */
    private CalculateElement calculateElement;

    /**
     * 右表达式
     */
    private Expression rightExpression;

    /**
     * 右括号
     */
    private RightBracket rightBracket;


    public CalculateExpression(String expression) {
        this.expression = expression;
        analysisExpression(expression);
    }

    private CalculateExpression(Expression leftExpression, CalculateElement calculateElement,
        Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.calculateElement = calculateElement;
        this.rightExpression = rightExpression;
        this.expression = leftExpression.toString() + calculateElement.toString() + rightExpression.toString();
    }

    @Override
    public Integer getValue() {
        return calculateElement.calculate(leftExpression.getValue(), rightExpression.getValue());
    }

    @Override
    public Integer getStartIndex() {
        return Objects.nonNull(leftBracket) ? leftBracket.getIndex() : leftExpression.getStartIndex();
    }

    @Override
    public Integer getEndIndex() {
        return Objects.nonNull(rightBracket) ? rightBracket.getIndex() : rightExpression.getEndIndex();
    }

    public void setLeftBracket(LeftBracket leftBracket) {
        this.leftBracket = leftBracket;
    }

    public void setRightBracket(RightBracket rightBracket) {
        this.rightBracket = rightBracket;
    }

    /**
     * 解析表达式
     *
     * @param expression example : 3*0+3+8+9*1 输出20   3+（3-0）*2 输出 9
     * @return
     */
    public void analysisExpression(String expression) {

        if (StringUtils.isBlank(expression)) {
            throw new IllegalArgumentException("expression is blank");
        }
        // 先把字符串解析成一个元素列表
        List<Element> elements = ExpressionUtils.analysis(expression);

        CalculateExpression res = (CalculateExpression) mergeElements(elements);

        this.rightBracket = res.rightBracket;
        this.leftBracket = res.leftBracket;
        this.leftExpression = res.leftExpression;
        this.calculateElement = res.calculateElement;
        this.rightExpression = res.rightExpression;
    }


    /**
     * 合并元素
     *
     * @param elements
     * @return
     */
    public Expression mergeElements(List<Element> elements) {
        List<Element> mergeEle = new LinkedList<>();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            //如果是一个左括号
            if (element instanceof LeftBracket) {
                LeftBracket leftBracket = (LeftBracket) element;
                Integer leftBracketIndex = leftBracket.getIndex();
                Integer rightBracketIndex = leftBracket.getRightBracket().getIndex();
                CalculateExpression expression = (CalculateExpression) mergeElements(
                    elements.subList(leftBracketIndex + 1, rightBracketIndex));
                expression.setLeftBracket(leftBracket);
                expression.setRightBracket(leftBracket.getRightBracket());
                mergeEle.add(expression);
                i += rightBracketIndex - leftBracketIndex;
                continue;
            }
            mergeEle.add(element);
        }
        return doAnalysis(mergeEle);
    }


    /**
     * 解析没有括号的情况下的元素列表为一个表达式
     *
     * @param elements
     */
    public Expression doAnalysis(List<Element> elements) {
        Map<CalculateElement, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            if (element instanceof CalculateElement) {
                treeMap.put((CalculateElement) element, i);
            }
        }
        ExpressionContainer container = new ArrayExpressionContainer(treeMap.size());

        for (CalculateElement element : treeMap.keySet()) {
            int curIndex = treeMap.get(element);
            Integer index = element.getIndex();
            int leftIndex = index - 1;
            int rightIndex = index + 1;
            //先从容器中取左边的表达式
            Expression leftExp = container.findByEndIndex(leftIndex);
            if (Objects.isNull(leftExp)) {
                leftExp = (Expression) elements.get(curIndex - 1);
            }
            Expression rightExp = container.findByStartIndex(rightIndex);
            if (Objects.isNull(rightExp)) {
                rightExp = (Expression) elements.get(curIndex + 1);
            }
            Expression expression = new CalculateExpression(leftExp, element, rightExp);
            container.fillExpression(expression);
        }
        return container.getExpression();
    }


    @Override
    public String toString() {
        return getObjectString(leftBracket) + leftExpression.toString() + calculateElement.toString() + rightExpression
            .toString() + getObjectString(rightBracket);
    }


    @Override
    public Integer getIndex() {
        return getStartIndex();
    }

    private String getObjectString(Object o) {
        return Objects.nonNull(o) ? o.toString() : "";
    }

    /**
     * 容器 用于存放每一步合并之后的表达式对象
     */
    interface ExpressionContainer {

        /**
         * 通过开始索引查找表达式对象
         *
         * @param startIndex
         * @return
         */
        Expression findByStartIndex(Integer startIndex);

        /**
         * 通过结束索引查找表达式对象
         *
         * @param endIndex
         * @return
         */
        Expression findByEndIndex(Integer endIndex);

        /**
         * 填充表达式
         *
         * @param expression
         */
        void fillExpression(Expression expression);

        /**
         * 获取最终合并完成的表达式
         *
         * @return
         */
        Expression getExpression();
    }

    /**
     * 基于数组的表达式容器
     */
    static class ArrayExpressionContainer implements ExpressionContainer {

        private Expression[] expressions;

        private int putIndex;

        public ArrayExpressionContainer(Integer size) {
            expressions = new Expression[size];
        }

        @Override
        public Expression findByStartIndex(Integer startIndex) {
            Integer index = findByCondition((e) -> e.getStartIndex().equals(startIndex));
            return index != -1 ? expressions[index] : null;
        }

        @Override
        public Expression findByEndIndex(Integer endIndex) {
            Integer index = findByCondition((e) -> e.getEndIndex().equals(endIndex));
            return index != -1 ? expressions[index] : null;
        }

        @Override
        public void fillExpression(Expression expression) {
            Integer startIndex = expression.getStartIndex();
            Integer indexOfStartExpression = findByCondition((e) -> e.getStartIndex().equals(startIndex));

            Integer endIndex = expression.getEndIndex();
            Integer indexOfEndExpression = findByCondition((e) -> e.getEndIndex().equals(endIndex));

            if (indexOfStartExpression != -1) {
                //如果已经存在容器中了 只需要覆盖原来的表达式
                expressions[indexOfStartExpression] = expression;
            }
            if (indexOfEndExpression != -1) {
                if (indexOfStartExpression != -1) {
                    //如果表达式已经存在容器中了，那么移除当前表达式,后面的表达式前移
                    for (int i = indexOfEndExpression; i < putIndex - 1; i++) {
                        expressions[i] = expressions[i + 1];
                    }
                    //help gc ~
                    expressions[--putIndex] = null;
                } else {
                    expressions[indexOfEndExpression] = expression;
                }
            }
            //开始和结束都不在容器中  说明是一个新的表达式 直接放入容器中
            if (indexOfStartExpression == -1 && indexOfEndExpression == -1) {
                expressions[putIndex++] = expression;
            }
        }

        @Override
        public Expression getExpression() {
            if (putIndex != 1) {
                throw new IllegalStateException("容器还没有填充完成");
            }
            return expressions[0];
        }

        /**
         * 通过条件获取到表达式在数组中的位置,如果没有找到 那么返回-1
         *
         * @param predicate
         * @return
         */
        private Integer findByCondition(Predicate<Expression> predicate) {
            for (int i = 0; i < putIndex; i++) {
                Expression cur = expressions[i];
                if (Objects.isNull(cur)) {
                    return -1;
                }
                if (predicate.test(cur)) {
                    return i;
                }
            }
            return -1;
        }
    }
}
