package priv.wangcheng.homework.calculator2;

import priv.wangcheng.homework.calculator2.decorate.LeftBracket;

/**
 * 表达式中的组成部分
 * <p>
 * 可能是一个数字 {@link priv.wangcheng.homework.calculator2.NumExpression}
 * <p>
 * 可能是一个计算符号 {@link priv.wangcheng.homework.calculator2.calculate.CalculateElement}
 * <p>
 * 可能是一个修饰符号 {@link LeftBracket}
 *
 * @author WangCheng
 * @version $ Id: Element.java, v0.1 2020/2/20 17:09 WangCheng Exp $$
 */
public interface Element {

    /**
     * 元素在表达式的位置
     *
     * @return
     */
    Integer getIndex();
}
