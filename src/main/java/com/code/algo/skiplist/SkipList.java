package com.code.algo.skiplist;

/**
 * 跳表
 * <p>链表加多级索引的数据结构，通过多级索引来提高查询效率，实现了基于链表的“二分查找”
 * <p>插入、删除、查询的时间复杂度都是O(logn)，空间复杂度是O(n)
 * @author panjb
 */
public class SkipList<T> {
    private static final float P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private final Node head = new Node(-1, null, MAX_LEVEL);
    private int levelCount = 1;

    public static void main(String[] args) {
        SkipList<String> list = new SkipList<>();
        list.insert(1, "a");
        list.insert(4, "g");
        list.insert(4, "c");
        list.insert(6, "d");
        String s = list.find(4);
        System.out.println("s = " + s);
    }

    /**
     * 插入操作
     * <p>先随机获取一个层数（插入新节点需要更新的层数），然后获取每一层的前驱节点，将节点插入到每一层，最后更新当前最大层数
     * @param key key
     * @param value value
     */
    public void insert(int key, T value) {
        //先获取随机层数
        int level = this.randomLevel();
        Node node = new Node(key, value, level);
        //updates[]用于存储需要每个层需要更新的节点（插入节点的前驱节点）
        Node[] updates = new Node[level];
        for (int i = 0; i < level; i++) {
            updates[i] = head;
        }
        //从高层开始，查找要插入节点node在每个层的前驱节点
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].key < key) {
                p = p.forwards[i];
            }
            if (level > i) {
                updates[i] = p;
            }
        }
        //将node插入到每一层
        for (int i = 0; i < level; i++) {
            node.forwards[i] = updates[i].forwards[i];
            updates[i].forwards[i] = node;
        }
        //更新当前层数
        if (level > levelCount) {
            levelCount = level;
        }
    }

    /**
     * 查找操作
     * @param key key
     * @return 存在返回对应的值，不存在返回null
     */
    public T find(int key) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].key < key) {
                p = p.forwards[i];
            }
        }
        //第一层的key是否相等
        if (p.forwards[0] != null && p.forwards[0].key == key) {
            return (T) p.forwards[0].value;
        }
        return null;
    }

    /***
     * 删除操作
     * <p>首先查找每一层的前驱节点，再判断第一层的key是否相等，若存在再删除每一层对应的节点
     * @param key key
     */
    public void delete(int key) {
        Node[] updates = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].key < key) {
                p = p.forwards[i];
            }
            updates[i] = p;
        }
        //查找的key是否存在
        if (p.forwards[0] != null && p.forwards[0].key == key) {
            //从最高层开始，删除每一层对应的节点
            for (int i = levelCount - 1; i >= 0; i--) {
                if (updates[i].forwards[i] != null && updates[i].forwards[i].key == key) {
                    updates[i].forwards[i] = updates[i].forwards[i].forwards[i];
                }
            }
        }
        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }
    }

    /**
     * <p>理论上一级索引中元素个数占原始数据的50%，二层索引为25%，三层12.5%，以此类推
     * <p>从概率的角度出发，即每一层晋升概率为50%：
     * <ul>
     *     <li>50%返回1</li>
     *     <li>25%返回2</li>
     *     <li>12.5%返回3</li>
     *     <li>...</li>
     * </ul>
     * @return 随机层数
     */
    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }


    private static class Node {
        private final int key;
        private final Object value;
        //当前节点的下一个节点，包含多层，对应层数通过下标表示
        private final Node[] forwards;

        public Node(int key, Object value, int level) {
            this.key = key;
            this.value = value;
            this.forwards = new Node[level];
        }
    }

}
