package priv.wangcheng.homework.calculator2.calculate;


import java.util.Objects;
import priv.wangcheng.homework.calculator2.CommonConstant;

/**
 * @author WangCheng
 * @version $ Id: SubCalculateSymbol.java, v0.1 2020/2/20 17:40 WangCheng Exp $$
 */
public class DivCalculateElement implements CalculateElement {

    private Integer index;

    public DivCalculateElement(Integer index) {
        this.index = index;
    }

    @Override
    public Integer doCalculate(Integer left, Integer right) {
        if (right == 0) {
            throw new IllegalArgumentException("/ by zero");
        }
        return left / right;
    }

    @Override
    public Integer getOrder() {
        return CommonConstant.ORDER_DIV;
    }

    @Override
    public String toString() {
        return String.valueOf(CommonConstant.CHAR_DIV);
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivCalculateElement that = (DivCalculateElement) o;
        return Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, toString());
    }
}
