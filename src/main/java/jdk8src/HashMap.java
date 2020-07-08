package jdk8src;

import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.IllegalFormatFlagsException;
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
     * 负载因子
     */
    final float loadFactor;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //  16

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

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
        //计算index = （n-1) & hash，如果没有值，直接添加【* 因为数组长度是2幂次，所以hash &（n-1) = hash % n， &效率更快】
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
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap;
        int newThr = 0;
        if (oldCap > 0) {
            //说明数组中已经存在元素
            if (oldCap >= MAXIMUM_CAPACITY) {
                //大于最大长度
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                        oldCap >= DEFAULT_INITIAL_CAPACITY) {
                //oldCap*2 小于最大值1 << 30 并且 大于等于16
                //扩容2倍
                newThr = oldCap << 1;
            }
        } else if (oldThr > 0) {
            //在创建map时用的是带参的构造函数，使threshold有初始值
            newCap = oldCap;
        } else {
            //oldCap = 0 数据还未创建
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            //1 当oldCap > 0 ，数组中已经存在元素，但长度<16
            //2 oldThr > 0；newCap = newThr; 第一次put
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY) ?
                    (int)ft : Integer.MAX_VALUE;
        }
        threshold = newThr;
        //创建新的节点数据，长度是扩容后新的长度
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            //遍历扩容前的原先数组，将元素迁移到新的数组上
            for (int j = 0; j < oldCap; j++) {
                Node<K,V> e;
                //原先数据上的元素赋值给e，判断当前是不是空，空的话，索引后移，一次遍历
                if ((e = oldTab[j]) != null) {
                    //原先数组元素置空
                    oldTab[j] = null;
                    //判断下个节点是不是空
                    if (e.next == null) {
                        //空，直接迁移到新的数组，计算新index=hash&(newCap-1)
                        newTab[e.hash & (newCap - 1)] = e;
                    } else if (e instanceof javax.swing.tree.TreeNode) {
                        //不是空，元素是TreeNode
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    } else {
                        //不是空，元素是链
                        //低位数组
                        Node<K,V> lowHead = null;
                        Node<K,V> lowTail = null;
                        //高位数组
                        Node<K,V> highHead = null;
                        Node<K,V> highTail = null;

                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                //说明元素在原始数组和新数组的index位置相同,在low链上即可
                                if (lowTail == null) {
                                    //链表没有元素
                                    lowHead = e;
                                } else {
                                    //链表有元素，放在下一个节点
                                    lowTail.next = e;
                                }
                                //当前元素e设为尾节点
                                lowTail = e;
                            } else {
                                //说明元素hash>原始数组长度,在high链上，index = 旧index + 旧数组长度【*也是因为数组长度是2幂次，才会这样】
                                if (highTail == null) {
                                    highHead = e;
                                } else {
                                    highTail.next = e;
                                }
                                highTail = e;
                            }
                        } while ((e = next) != null);
                        if (lowTail != null) {
                            lowTail.next = null;
                            newTab[j] = lowHead;
                        }
                        if (highTail != null) {
                            highTail.next = null;
                            newTab[j + oldCap] = highHead;
                        }
                    }
                }
            }
        }
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

        final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {

        }


    }
}
