package per.wsk.java.study02;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一、Map的实现类的结构：
 *  |----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
 *
 *         |----HashMap:作为Map的主要实现类；线程不安全的，效率高；可以存储null的key和value
 *              |----LinkedHashMap:HashMap的子类，保证在遍历map元素时，可以按照添加的顺序实现遍历。
 *                      原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 *                      对于频繁的遍历操作，此类执行效率高于HashMap。
 *         |----TreeMap:保证按照添加的key-value对进行排序，TreeMap是真的key排序的，实现排序遍历。
 *                      此时考虑key的自然排序或定制排序。
 *                      TreeMap底层使用红黑树
 *         |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
 *              |----Properties:Hashtable的子类，线程安全的，常用来处理配置文件。
 *                              key和value都是String类型
 *
 *      HashMap、LinkedHashMap、TreeMap都是线程不安全的。
 *      Properties、Hashtable是线程安全的。
 *
 *      HashMap的底层：数组+链表  （jdk7及之前）
 *                    数组+链表+红黑树 （jdk 8）
 *      Hashtable、Properties底层和HashMap一样。
 *
 *  面试题：
 *  1. HashMap的底层实现原理？
 *  2. HashMap 和 Hashtable的异同？
 *  3. ConcurrentHashMap 与 Hashtable的异同？
 *      简单解释：Hashtable是线程安全的，但相当于操作Hashtable对象时是单线程操作，多个线程操作同一个Hashtable需要
 *                排队，效率低。
 *                ConcurrentHashMap也是线程安全的，但它在多个线程同时操作一个ConcurrentHashMap对象时，效率比Hashtable
 *                要高，ConcurrentHashMap底层通过分段锁，多个线程操作一个ConcurrentHashMap对象时，ConcurrentHashMap
 *                给每个线程分出一段，假设有n个线程同时操作ConcurrentHashMap，ConcurrentHashMap会
 *                分出n段，这n段的任意两段都没有交集，这样多个线程可以同时操作一个ConcurrentHashMap对象。
 *
 *
 *  二、Map结构的理解：
 *    Map中的key: 无序的、不可重复的，使用Set存储所有的key  ---> 因为key是用set存的，key不能重复，以HashMap为例，
 *                                                           HashMap的key所在的类要重写equals()和hashCode()方法，
 *                                                           以TreeMap为例，TreeMap是把key排序的，所以TreeMap的key
 *                                                           所在的类必须继承Comparable接口或使用Compartor方法
 *
 *    Map中的value: 无序的、可重复的，使用Collection存储所有的value。
 *                   Map中有containsValue()方法，判断当前map是否包含这个value，所以 ----> value所在的类要重写equals()
 *
 *    一个键值对：key-value构成了一个Entry对象。
 *    Entry对象是Map中的一个内部类。
 *
 *    Map中的entry:无序的、不可重复的，使用Set存储所有的entry
 *
 *  三、HashMap的底层实现原理？以jdk7为例说明：
 *
 *      HashMap map = new HashMap():
 *      在实例化以后，底层创建了长度是16的Entry类型的（一个键值对构成一个Entry对象，Entry是HashMap中的一个内部类）数组，
 *       这个Entry类型的数组名字叫table，  Entry[] table。
 *
 *      ...可能已经执行过多次put...
 *      map.put(key1,value1):
 *      首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置。
 *      如果此位置上的数据为空，此时的key1-value1添加成功。 ----情况1
 *      如果此位置上的数据不为空，(意味着此位置上存在一个或多个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据
 *      的哈希值：
 *              如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。----情况2
 *              如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继续比较：调用key1所在类的equals(key2)方法，比较：
 *                      如果equals()返回false:此时key1-value1添加成功。----情况3
 *                      如果equals()返回true:使用value1替换value2。（所以使用相同key再次put时做的是修改value的操作）
 *
 *       补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。
 *
 *      在不断的添加过程中，会涉及到扩容问题，当超出临界值(且要存放的位置非空)时，扩容。
 *      默认的扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来。
 *
 *      jdk8 相较于jdk7在底层实现方面的不同：
 *      1. new HashMap():实例化HashMap对象时底层没有创建一个长度为16的数组，
 *          首次调用put()方法时，底层创建长度为16的数组
 *      2. jdk 8底层的数组是：Node[],而非Entry[]，也就是说jdk8中把内部类Entry改名叫了Node。
 *      3. jdk7底层结构只有：数组+链表。jdk8中底层结构：数组+链表+红黑树。
 *         3.1 形成链表时，七上八下（jdk7:新的元素指向旧的元素。jdk8：旧的元素指向新的元素）
           3.2 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64时，
                此时数组中此索引位置上的所有数据由之前的链表存储改为使用红黑树存储。
 *
 *
 *      HashMap的容量指的是HashMap中的数组的长度，因为数组的每个元素可以是链表或红黑树，而链表和红黑树都可以
 *      存放多个元素，所以，HashMap的最多能存放的元素的数量并不是HashMap的容量。
 *
 *      DEFAULT_INITIAL_CAPACITY : HashMap的默认容量，16
 *      DEFAULT_LOAD_FACTOR：HashMap的默认加载因子：0.75
 *      threshold：扩容的临界值，=容量*填充因子：16 * 0.75 => 12
 *      TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值，转化为红黑树:8
 *      MIN_TREEIFY_CAPACITY：桶中的Node被树化时最小的hash表容量:64
 *      （当一个链表的元素个数大于8个且map的数组的长度大于64，此时会把这个链表变成红黑树结构）
 *
 *  四、LinkedHashMap的底层实现原理（了解）
 *      源码中：
 *      static class Entry<K,V> extends HashMap.Node<K,V> {
             Entry<K,V> before, after;//能够记录添加的元素的先后顺序
             Entry(int hash, K key, V value, Node<K,V> next) {
                super(hash, key, value, next);
             }
         }

        LinkedHashMap继承HashMap，底层也是 数组+双向链表+红黑树的结构。
        LinkedHashMap相比较HashMap,LinkedHashMap新增了一套双向链表，这个双向链表的前后顺序
        是和元素的添加顺序一致的。
        下面test02方法中，从输出结果可以看到，HashMap的输出结果是按照元素在HashMap
        底层的存放顺序输出的， LinkedHashMap的输出结果是按照元素的添加顺序输出的。

 *
 *
 *   五、Map中定义的方法：
     添加、删除、修改操作：
     Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
     void putAll(Map m):将m中的所有key-value对存放到当前map中
     Object remove(Object key)：移除指定key的key-value对，并返回value
     void clear()：清空当前map中的所有数据
     元素查询的操作：
     Object get(Object key)：获取指定key对应的value
     boolean containsKey(Object key)：是否包含指定的key
     boolean containsValue(Object value)：是否包含指定的value
     int size()：返回map中key-value对的个数
     boolean isEmpty()：判断当前map是否为空
     boolean equals(Object obj)：判断当前map和参数对象obj是否相等
     元视图操作的方法：
     Set keySet()：返回所有key构成的Set集合
     Collection values()：返回所有value构成的Collection集合
     Set entrySet()：返回所有key-value对构成的Set集合

 *总结：常用方法：
 * 添加：put(Object key,Object value)
 * 删除：remove(Object key)
 * 修改：put(Object key,Object value)
 * 查询：get(Object key)
 * 长度：size()
 * 遍历：keySet() / values() / entrySet()
 *
 *
 * @author shkstart
 * @create 2019 上午 11:15
 */
public class MapTest {

    /*
    HashMap的key和value都可以为null,但Hashtable不行。
    详细原因需要看HashMap和Hashtable中的put()方法
     */
    @Test
    public void test1(){
        Map map = new HashMap();
//        map = new Hashtable();
        map.put(null,123);
    }


    @Test
    public void test2(){
        Map map = new HashMap();
        map.put(123,"AA");
        map.put(345,"BB");
        map.put(12,"CC");

        System.out.println(map);//{345=BB, 123=AA, 12=CC}

        Map map2 = new LinkedHashMap();
        map2.put(123,"AA");
        map2.put(345,"BB");
        map2.put(12,"CC");

        System.out.println(map2);//{123=AA, 345=BB, 12=CC}
    }


    /*
     添加、删除、修改操作：
 Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
 void putAll(Map m):将m中的所有key-value对存放到当前map中
 Object remove(Object key)：移除指定key的key-value对，并返回当前删除的键值对中的value
 void clear()：清空当前map中的所有数据
     */
    @Test
    public void test3(){
        Map map = new HashMap();
        //添加
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        //修改
        map.put("AA",87);

        System.out.println(map);//  {AA=87, BB=56, 45=123}

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);

        map.putAll(map1);

        System.out.println(map);//   {AA=87, BB=56, CC=123, DD=123, 45=123}

        //remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);//   123
        System.out.println(map);//  {AA=87, BB=56, DD=123, 45=123}

        //clear()
        map.clear();//并不是把map变成null，也不是把map中的底层的数组变成null，
                    // 是把map底层的数组的每个位置的元素都变成null
        System.out.println(map.size()); //  0
        System.out.println(map);//  {}
    }

    /*
    元素查询的操作：
    Object get(Object key)：获取指定key对应的value
    boolean containsKey(Object key)：是否包含指定的key
    boolean containsValue(Object value)：是否包含指定的value
    int size()：返回map中key-value对的个数
    boolean isEmpty()：判断当前map是否为空
    boolean equals(Object obj)：判断当前map和参数对象obj是否相等
    */
    @Test
    public void test4(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        // Object get(Object key)
        System.out.println(map.get(45));
        //containsKey(Object key)  是否包含这个key，
        /*
        底层是先用hashCode计算出应该存放的位置，计算这个位置是否有元素，没有就结束，返回false。
        如果这个位置有元素，拿这个位置的每个元素的hashCode与当前元素对比，如果每次比较的hashCode都不同，
        返回false。  如果存在hashCode相同的，把每个hashCode相同的元素一一与当前元素进行equals()比较，
        有一个equals()时返回true就结束方法，返回true,所有equals()方法都返回false，结束方法，返回false。
         */
        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);

        //判断是否包含这个value，拿map中每个元素的value与当前value进行equals()方法比较。
        isExist = map.containsValue(123);
        System.out.println(isExist);

        map.clear();

        //isEmpty()是 map中的size是否是0
        System.out.println(map.isEmpty());

    }


    /*
     遍历map的方法：
     Set keySet()：返回所有key构成的Set集合
     Collection values()：返回所有value构成的Collection集合
     Set entrySet()：返回所有key-value对构成的Set集合
     */
    @Test
    public void test5(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,1234);
        map.put("BB",56);

        //遍历所有的key：keySet()
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("------------------------------------");

        //遍历所有的value：values()
        Collection values = map.values();
        for(Object obj : values){
            System.out.println(obj);
        }

        System.out.println("------------------------------------");

        //遍历所有的key-value
        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }

        System.out.println("----------------------------------------------");

        //方式二：
        Set keySet = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while(iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key + "=====" + value);

        }

    }



}
