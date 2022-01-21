package com.code.algo.stack;

/**
 * 顺序栈：基于数组实现的栈，支持动态扩容
 * <p>出栈、入栈时间复杂度：O(1) 均摊复杂度
 *
 * @author panjb
 */
public class StackBaseArray<T> {
    private Object[] items;
    private int capacity;
    private int count;

    public StackBaseArray(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.count = 0;
    }

    public void push(T item) {
        if (count == capacity) {
            resize();
        }
        items[count] = item;
        count++;
    }

    /**
     * 动态扩容为原来2倍
     */
    private void resize() {
        capacity = capacity << 1;
        Object[] newItems = new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    public T pop() {
        if (count == 0) {
            return null;
        }
        T item = (T) items[count - 1];
        count--;
        return item;
    }
}
