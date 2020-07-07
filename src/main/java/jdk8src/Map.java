package jdk8src;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/7
 */
public interface Map<K,V> {

    /**
     * 插入
     * @param key
     * @param value
     * @return
     *
     * 1.put将指定的<key,value>键值对插入
     * 2.有返回值V，返回值是key原先所映射的value值 或者 null（这个key原先没有映射）
     * （返回null也有可能是<null,null>）
     */
    V put(K key, V value);


    interface Entry<K,V> {
        K getKey();
        V getValue();
        V setValue(V value);

        boolean equeals(Object o);

        @Override
        int hashCode();
    }
}
