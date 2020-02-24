package priv.wangcheng.homework.queue;

/**
 * @author WangCheng
 * @version $Id: Queue.java, v0.1 2020/2/18 13:07 WangCheng Exp $$
 */
public interface Queue<E extends Comparable<E>> {

    /**
     * 入队列
     *
     * @param e
     * @return
     */
    E enqueue(E e);

    /**
     * 出队列
     *
     * @return
     */
    E dequeue();

    /**
     * 最大元素
     *
     * @return
     */
    E maxElement();
}
