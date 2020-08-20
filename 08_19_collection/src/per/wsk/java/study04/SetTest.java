package per.wsk.java.study04;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1. Set接口的框架：
 *
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 *                  |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历
 *                                      对于频繁的遍历操作，LinkedHashSet效率高于HashSet.
 *              |----TreeSet：可以按照添加对象的指定属性，进行排序。
 *
 *
 *  1. Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法。
 *
 *  2. 要求：向Set(主要指：HashSet、LinkedHashSet)中添加的数据，其所在的类一定要重写hashCode()和equals()
 *     要求：重写的hashCode()和equals()尽可能保持一致性：相等的对象必须具有相等的散列码（散列码即哈希码）
 *      重写两个方法的小技巧：对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。
 *
 *
 * @author shkstart
 * @create 2019 下午 3:40
 */
public class SetTest {
    /*
    一、Set：存储无序的、不可重复的数据
    以HashSet为例说明：
    1. 无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据每个元素的哈希值，再用
        元素的哈希值进行一定的算法计算决定元素的位置的。

    2. 不可重复性：详见下面的set集合添加元素的过程。

        set集合添加元素一般遵循这样的规则：
            set集合中的任意两个元素按照equals()判断时，都不能返回true，也就是说，如果要添加的新元素
            与set集合中某一个已有的元素进行equals()判断返回的结果是true，那么这个新元素就添加失败。

    二、添加元素的过程（也是set集合的去重原理）：以HashSet为例：

        我们向HashSet中添加元素a, 首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
        此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引位置），判断
        数组此位置上是否已经有元素：
            ①如果此位置上没有其他元素，则元素a添加成功。         --->情况1
            ②如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值：
                ②.①：如果hash值不相同，则元素a添加成功，此时元素a是以链表的形式添加到该位置的  --->情况2
                ②.②: 如果hash值相同，进而需要调用元素a所在类中的equals()方法，
                       元素a与元素b通过equals()方法进行比较。
                       ②.②.①：equals()返回true,元素a添加失败
                       ②.②.②：equals()返回false,则元素a添加成功,此时元素a是以链表的形式添加到该位置的  --->情况3

        对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。
        jdk 7 :元素a放到数组中，指向原来的元素。
        jdk 8 :原来的元素在数组中，指向元素a。


        以上所讲到的就是set集合的add()方法的执行流程。


        HashSet底层：底层用的HashMap结构，HashMap底层用的 数组+链表+红黑树的结构。

     */

    @Test
    public void test1(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        //下面两行添加的是两个User对象，两个对象的属性的值都一样，
        //根据上面set集合去重原理 所讲的，添加时会先调用hashCode方法，计算hash值，
        //当User类中没有重写hashCode()方法时，调用的是Object中的hashCode()方法，Object中的hashCode()方法
        //可以认为是返回的一个随机的hash值，  这样的话，虽然下面两个User对象属性的值都一样，但hash值却不一样
        //根据上面讲到的set集合添加元素的流程，根据该对象的hash值进行一定的算法，计算该对象应该添加到的
        //set集合中的位置，因为hash值不一样，所以大概率计算出的位置也不一样，只有当计算出的位置一样时才会调用
        //equals()方法看返回结果，返回true添加失败，返回false就通过链表的格式添加。
        //因为hash值不一样导致大概率返回的应该添加的位置不一样，这样就直接添加成功了，没有调用equals()方法，
        //所以，当下面的User类中重写了equals()方法但没有重写hashCode()方法时，下面两个对象都添加成功了，且没有调用
        //equals()方法。
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    //LinkedHashSet
    /*
    LinkedHashSet是HashSet的子类，HashSet底层用的HashMap结构，
    LinkedHashSet底层用的也是HashMap结构，HashMap底层用的 数组+链表+红黑树的结构
     */


    //LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个
    //数据和后一个数据。  即LinkedHashSet底层既是顺序表结构也是双向链表结构。
    //底层也是一个数组，添加元素时，和HashSet一样，通过每个元素的hashCode决定添加到的数组中的位置，
    //这样，底层的元素仍是无序的(即并不是按照元素的添加顺序排序，而是通过每个元素的hash值排序)
    //但底层同时又是链表结构，添加到的第一个元素的next域指向添加的第二个元素，第二个元素的上一个域指向添加的第一个
    //元素，也就是说，这个双向链表的顺序是按照元素的添加顺序排序的。LinkedHashSet在通过迭代器遍历时，是按照
    //底层的双向链表的顺序遍历的，不是按照底层的数组的下标顺序遍历的。所以下面的test2()方法的输出顺序是和元素
    //的添加顺序是一样的。

    //优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet
    @Test
    public void test2(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        /*
        LinkedHashSet插入性能略低于 HashSet，但在迭代访问 Set 里的全
        部元素时有很好的性能
         */
    }
}
