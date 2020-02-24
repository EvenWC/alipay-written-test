package priv.wangcheng.homework.calculator2;

import java.util.Objects;
import priv.wangcheng.homework.calculator2.calculate.AddCalculateElement;
import priv.wangcheng.homework.calculator2.calculate.DivCalculateElement;
import priv.wangcheng.homework.calculator2.calculate.MultiCalculateElement;
import priv.wangcheng.homework.calculator2.calculate.SubCalculateElement;
import priv.wangcheng.homework.calculator2.decorate.LeftBracket;
import priv.wangcheng.homework.calculator2.decorate.RightBracket;

/**
 * @author WangCheng
 * @version $ Id: SymbolFactory.java, v0.1 2020/2/21 10:24 WangCheng Exp $$
 */
public class SingleCharSymbolFactory {

    /**
     * 通过传入的字符  + - * / （ ） 等创建一个Symbol 对象
     *
     * @param character
     * @param index
     * @return
     */
    public static Element createSymbol(Character character, Integer index) {

        Element element;

        switch (character) {
            case '+':
                element = new AddCalculateElement(index);
                break;
            case '-':
                element = new SubCalculateElement(index);
                break;
            case '*':
                element = new MultiCalculateElement(index);
                break;
            case '/':
                element = new DivCalculateElement(index);
                break;
            case '(':
                element = new LeftBracket(index);
                break;
            case ')':
                element = new RightBracket(index);
                break;
            default:
                element = null;
                break;
        }
        if (Objects.isNull(element)) {
            throw new IllegalArgumentException(String.format("The symbol 【%s】 is not supported", character));
        }
        return element;
    }

}
