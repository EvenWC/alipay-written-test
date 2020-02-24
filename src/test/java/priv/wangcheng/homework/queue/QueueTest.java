package priv.wangcheng.homework.queue;

import java.util.LinkedList;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author WangCheng
 * @version $ Id: QueueTest.java, v0.1 2020/2/18 21:01 WangCheng Exp $$
 */
public class QueueTest {

    @Test
    public void test_queue() {

        //入队列
        Queue<Integer> queue = new ArrayQueue<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Integer integer = queue.enqueue(i);
            if (Objects.nonNull(integer)) {
                list.add(integer);
            }
        }
        Assert.assertEquals("队列容量为16,只能入这么多数量", list.size(), (int) ArrayQueue.DEFAULT_INITIAL_CAPACITY);

        //出队列
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Integer integer = queue.dequeue();
            if (Objects.nonNull(integer)) {
                list2.add(integer);
            }
        }
        Assert.assertEquals("队列容量为16，只能取这么多数量", list.size(), (int) ArrayQueue.DEFAULT_INITIAL_CAPACITY);

        //查找最大的元素

        Queue<Integer> queue2 = new ArrayQueue<>();

        queue2.enqueue(2);
        queue2.enqueue(1);
        queue2.enqueue(3);
        queue2.enqueue(6);
        queue2.enqueue(10);
        queue2.enqueue(7);
        queue2.enqueue(9);
        queue2.enqueue(4);

        Assert.assertEquals("最大值为10", Integer.valueOf(10), queue2.maxElement());

    }


    @Test
    public void test_linked() {
        Queue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(22);
        queue.enqueue(10);
        Integer dequeue1 = queue.dequeue();
        Assert.assertEquals(Integer.valueOf(22), dequeue1);
        Integer dequeue2 = queue.dequeue();
        Assert.assertEquals(Integer.valueOf(10), dequeue2);

    }

}
