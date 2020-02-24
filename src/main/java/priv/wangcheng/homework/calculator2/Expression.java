package priv.wangcheng.homework.calculator2;


/**
 * @author WangCheng
 * @version $ Id: Expression.java, v0.1 2020/2/18 18:01 WangCheng Exp $$
 */
public interface Expression extends Element {

    /**
     * 获取表达式的值
     *
     * @return
     */
    Integer getValue();

    /**
     * 表达式开始位置
     *
     * @return
     */
    Integer getStartIndex();

    /**
     * 表达式结束位置
     *
     * @return
     */
    Integer getEndIndex();
}
