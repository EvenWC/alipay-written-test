package priv.wangcheng.homework.calculator2.decorate;

import priv.wangcheng.homework.calculator2.Element;

/**
 * @author WangCheng
 * @version $ Id: AbstractBracket.java, v0.1 2020/2/21 11:15 WangCheng Exp $$
 */
public abstract class AbstractBracket implements Element {


    /**
     * 位置
     */
    private Integer index;
    /**
     * 包含了几层括号 （包括自己）
     */
    private Integer layerNum;

    public AbstractBracket(Integer index) {
        this.index = index;
    }

    public Integer getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Integer layerNum) {
        this.layerNum = layerNum;
    }

    @Override
    public Integer getIndex() {
        return index;
    }
}
