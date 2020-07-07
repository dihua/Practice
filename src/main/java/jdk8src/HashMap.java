package jdk8src;

import java.util.Objects;

/**
 * @author dihua.wu
 * @description hashmap 源码
 * @create 2020/7/7
 */
public class HashMap<K, V> extends AbstractMap<K, V> {

    transient Node<K, V>[] table;

    static final int TREEIFY_THRESHOLD = 8;

    transient int modCount;

    /**
     * 键值对数量
     */
    transient int size;

    /**
     * 阈值
     */
    int threshold;

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n;
        int i;
        int hash = hash(key);

        //先判断数组table是不是空
        if ((tab = table) == null || (n = table.length) == 0) {
            //初始化元素数组
            n = (tab = resize()).length;
        }
        //计算index = n-1) & hash，如果没有值，直接添加
        if ((p = tab[(i = (n-1)) & hash]) == null) {
            tab[i] = new Node<>(hash, key, value, null);
        } else {
            //该index有值，先看tab[i]是不是一样的，再判断是链表还是树
            K k;
            Node<K,V> e;
            //判断tab[i]是不是一样， hash和key都相等，赋值给临时变量e
            boolean equalFlag1 = (p.hash == hash) && (((k = p.key) == key) || (key != null && key.equals(k)));
            if (equalFlag1) {
                e = p;
            } else if (p instanceof TreeNode) {
                //树
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            } else {
                //链，遍历链表，尾插法
                for (int binCount = 0; ; ++binCount) {
                    //当前这个节点的下个节点为空，说明是链表的尾节点，直接添加
                    if ((e = p.next) == null) {
                        p.next = new Node<>(hash, key, value, null);
                        //添加后判断是否需要树化
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    boolean equalFlag2 = (e.hash == hash) && (((k = e.key) == key) || (key != null && key.equals(k)));
                    if (equalFlag2) {
                        //链上找到一样的key
                        break;
                    }
                    p = e;
                }
            }
            //找到和插入的key相同的节点e
            if (e != null) {
                V oldValue = e.value;
                //将插入的value覆盖原有value
                e.value = value;
                //返回原有value
                return oldValue;
            }
        }
        // 新增一个节点后，修改计数器递增
        ++modCount;
        //判断当前所有键值对节点时候是否大于扩容的域
        //size 键值对数量+1
        if (++size > threshold) {
            //扩容
            resize();
        }
        return null;
    }

    final Node<K,V>[] resize() {
        Node<K,V>[] newTab = new Node[0];
        return newTab;
    }

    final void treeifyBin(Node<K,V>[] tab, int hash) {

    }

    static class Node<K, V> implements Map.Entry<K, V> {
        //此处的hash是key的hash值hash(key)
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public boolean equeals(Object o) {
            return false;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                //强转，赋临时变量
                Map.Entry<?,?> e = (Entry<?, ?>) o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    static final int hash(Object key) {
        return 0;
    }


    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        //???作用
        TreeNode<K,V> prev;

        boolean red;
        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }

        final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                       int h, K k, V v) {
            return null;
        }
    }
}
