package priv.wangcheng.homework.calculator2.decorate;

import priv.wangcheng.homework.calculator2.CommonConstant;

/**
 * @author WangCheng
 * @version $ Id: RightBracket.java, v0.1 2020/2/21 10:39 WangCheng Exp $$
 */
public class RightBracket extends AbstractBracket {

    /**
     * 左括号
     */
    private LeftBracket leftBracket;

    public RightBracket(Integer index) {
        super(index);
    }

    public LeftBracket getLeftBracket() {
        return leftBracket;
    }

    public void setLeftBracket(LeftBracket leftBracket) {
        this.leftBracket = leftBracket;
    }

    @Override
    public String toString() {
        return String.valueOf(CommonConstant.CHAR_RIGHT_BRACKET);
    }
}
