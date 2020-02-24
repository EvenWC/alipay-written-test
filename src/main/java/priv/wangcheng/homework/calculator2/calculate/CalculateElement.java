package priv.wangcheng.homework.calculator2.calculate;

import java.util.Objects;
import priv.wangcheng.homework.calculator2.Element;

/**
 * 计算符号
 *
 * @author WangCheng
 * @version $ Id: CalculateSymbol.java, v0.1 2020/2/20 17:11 WangCheng Exp $$
 */
public interface CalculateElement extends Element, Comparable<CalculateElement> {

    /**
     * 计算
     *
     * @param left
     * @param right
     * @return
     */
    default Integer calculate(Integer left, Integer right) {
        if (Objects.isNull(left)) {
            throw new IllegalArgumentException("left is null");
        }
        if (Objects.isNull(right)) {
            throw new IllegalArgumentException("right is null");
        }
        return doCalculate(left, right);
    }

    /**
     * 比较优先级 order越小优先级越高
     *
     * @param element
     * @return
     */
    @Override
    default int compareTo(CalculateElement element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Integer targetOrder = element.getOrder();
        Integer curOrder = this.getOrder();
        if (targetOrder.equals(curOrder)) {
            //如果优先级相同的 那么比较当前计算符号的位置，位置靠前的优先级高
            Integer targetIndex = element.getIndex();
            Integer curIndex = this.getIndex();
            return curIndex.compareTo(targetIndex);
        }
        if (curOrder < targetOrder) {
            return -1;
        }
        return 1;
    }


    /**
     * 执行计算
     *
     * @param left
     * @param right
     * @return
     */
    Integer doCalculate(Integer left, Integer right);

    /**
     * 优先级
     *
     * @return
     */
    Integer getOrder();
}
