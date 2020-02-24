package priv.wangcheng.homework.calculator2;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import priv.wangcheng.homework.calculator2.decorate.LeftBracket;
import priv.wangcheng.homework.calculator2.decorate.RightBracket;

/**
 * 表达式工具类
 *
 * @author WangCheng
 * @version $ Id: ExpressionUtils.java, v0.1 2020/2/21 10:02 WangCheng Exp $$
 */
public class ExpressionUtils {

    /**
     * 解析字符串表达式 返回一个符号列表
     *
     * @param expression
     * @return
     */
    public static List<Element> analysis(String expression) {

        if (StringUtils.isBlank(expression)) {
            throw new IllegalArgumentException("expression is blank");
        }
        char[] chars = expression.toCharArray();
        List<Element> elements = new LinkedList<>();
        StringBuilder numStr = new StringBuilder();
        // index 当前符号在list中的位置
        Integer index = 0;
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (NumExpression.isNum(cur)) {
                numStr.append(cur);
                if (i == chars.length - 1) {
                    elements.add(NumExpression.valueOf(numStr.toString(), index));
                    index++;
                }
            } else {
                // 先处理前面的数字
                if (numStr.length() > 0) {
                    elements.add(NumExpression.valueOf(numStr.toString(), index));
                    numStr = new StringBuilder();
                    index++;
                }
                Element element = SingleCharSymbolFactory.createSymbol(cur, index);
                // 如果是右括号, 向左遍历找到 最近一个  没有设置右括号的 左括号符号对象
                if (element instanceof RightBracket) {
                    RightBracket rightBracket = (RightBracket) element;
                    LeftBracket next = null;
                    for (int j = index - 1; j >= 0; j--) {
                        Element ps = elements.get(j);
                        if (ps instanceof LeftBracket) {
                            LeftBracket leftBracket = (LeftBracket) ps;
                            RightBracket rb = leftBracket.getRightBracket();
                            if (Objects.isNull(rb)) {
                                int layerNum = 1;
                                if (Objects.nonNull(next)) {
                                    layerNum = next.getLayerNum() + 1;
                                }
                                leftBracket.setLayerNum(layerNum);
                                leftBracket.setRightBracket(rightBracket);
                                rightBracket.setLayerNum(layerNum);
                                rightBracket.setLeftBracket(leftBracket);
                                break;
                            } else {
                                next = leftBracket;
                            }
                        }
                    }
                }
                elements.add(element);
                index++;
            }
        }
        return elements;
    }
}
