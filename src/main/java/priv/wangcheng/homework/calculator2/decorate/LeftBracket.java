package priv.wangcheng.homework.calculator2.decorate;

import priv.wangcheng.homework.calculator2.CommonConstant;

/**
 * @author WangCheng
 * @version $ Id: LeftBracket.java, v0.1 2020/2/21 10:33 WangCheng Exp $$
 */
public class LeftBracket extends AbstractBracket {

    /**
     * 右边括号的索引
     */
    private RightBracket rightBracket;

    public LeftBracket(Integer index) {
        super(index);
    }

    public void setRightBracket(RightBracket rightBracket) {
        this.rightBracket = rightBracket;
    }

    public RightBracket getRightBracket() {
        return rightBracket;
    }

    @Override
    public String toString() {
        return String.valueOf(CommonConstant.CHAR_LEFT_BRACKET);
    }
}
