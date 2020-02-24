package priv.wangcheng.homework.queue;

/**
 * @author WangCheng
 * @version $ Id: ArrayQueue.java, v0.1 2020/2/18 20:09 WangCheng Exp $$
 */
public class ArrayQueue<E extends Comparable<E>> implements Queue<E> {

    /**
     * 存放元素的数组
     */
    private Object[] elements;
    /**
     * 默认容量
     */
    public static final Integer DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * 最后的索引
     */
    int endIndex;

    public ArrayQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayQueue(Integer capacity) {
        elements = new Object[capacity];
    }

    @Override
    public E enqueue(E e) {
        final Object[] items = this.elements;
        if (endIndex < items.length) {
            items[endIndex] = e;
            endIndex++;
            return e;
        }
        return null;
    }

    @Override
    public E dequeue() {
        final Object[] items = this.elements;
        // 从下标为0的位置出
        if (items[0] != null) {
            @SuppressWarnings("unchecked")
            E item = (E) items[0];
            move();
            endIndex--;
            return item;
        }
        return null;
    }

    private void move() {
        for (int i = 0; i < this.elements.length - 1; i++) {
            Object next = this.elements[i + 1];
            if (next != null) {
                this.elements[i] = next;
            } else {
                this.elements[i] = null;
                break;
            }

        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public E maxElement() {
        E maxEle = (E) elements[0];
        for (int i = 0; i < endIndex; i++) {
            E currentEle = (E) elements[i];
            if (currentEle.compareTo(maxEle) > 0) {
                maxEle = currentEle;
            }
        }
        return maxEle;
    }

}
