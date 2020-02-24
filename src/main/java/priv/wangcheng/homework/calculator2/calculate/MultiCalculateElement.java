package priv.wangcheng.homework.calculator2.calculate;


import java.util.Objects;
import priv.wangcheng.homework.calculator2.CommonConstant;

/**
 * @author WangCheng
 * @version $ Id: MultiCalculateSymbol.java, v0.1 2020/2/20 17:40 WangCheng Exp $$
 */
public class MultiCalculateElement implements CalculateElement {

    private Integer index;

    public MultiCalculateElement(Integer index) {
        this.index = index;
    }

    @Override
    public Integer doCalculate(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer getOrder() {
        return CommonConstant.ORDER_MULTI;
    }

    @Override
    public String toString() {
        return String.valueOf(CommonConstant.CHAR_MULTI);
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
        MultiCalculateElement that = (MultiCalculateElement) o;
        return Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, toString());
    }
}
