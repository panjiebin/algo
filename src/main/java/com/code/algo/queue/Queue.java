package com.code.algo.queue;

/**
 * 队列
 *
 * @author panjb
 */
public interface Queue<E> {

    /**
     * 入队
     * @param e 元素
     * @return 成功加入队列-true，反之-false
     */
    boolean enqueue(E e);

    /**
     * 出队
     * @return 队头元素
     */
    E dequeue();
}
