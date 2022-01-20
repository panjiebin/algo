package com.code.algo.stack;

/**
 * 基于数组实现的栈，不支持扩容
 * <p>出栈、入栈时间复杂度：O(1)
 * @author panjb
 */
public class StackBaseArray<T> {

    private final Object[] items;
    private final int capacity;
    private int count;

    public StackBaseArray(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.count = 0;
    }

    public boolean push(T item) {
        if (count >= capacity) {
            return false;
        }
        this.items[count] = item;
        count++;
        return true;
    }

    public T pop() {
        if (count == 0) {
            return null;
        }
        T t = (T) this.items[count - 1];
        count--;
        return t;
    }
}
