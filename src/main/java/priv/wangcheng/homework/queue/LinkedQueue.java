package priv.wangcheng.homework.queue;

import java.util.Objects;

/**
 * @author WangCheng
 * @version $ Id: LinkedQueue.java, v0.1 2020/2/20 10:22 WangCheng Exp $$
 */
public class LinkedQueue<E extends Comparable<E>> implements Queue<E> {


    static class Node<E> {

        E e;

        Node<E> next;

        private Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }
    }

    /**
     * 队列头
     */
    private Node<E> head;

    /**
     * 队尾
     */
    private Node<E> tail;


    @Override
    public E enqueue(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (Objects.isNull(head)) {
            initQueue(newNode);
        } else {
            addWaiter(newNode);
        }
        return e;
    }

    @Override
    public E dequeue() {
        if (Objects.isNull(head)) {
            throw new IllegalStateException("队列为空");
        }
        E e = head.e;
        head = head.next;
        return e;
    }

    @Override
    public E maxElement() {
        if (Objects.isNull(head)) {
            throw new IllegalStateException("队列为空");
        }
        E max = head.e;
        Node<E> curr;
        while ((curr = head.next) != null) {
            if (curr.e.compareTo(max) > 0) {
                max = curr.e;
            }
        }
        return max;
    }

    /**
     * 初始化队列
     *
     * @param node
     */
    private void initQueue(Node<E> node) {
        head = node;
        tail = node;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    private void addWaiter(Node<E> node) {
        tail.next = node;
    }
}
