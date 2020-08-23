package per.wsk.java.study03;

import org.junit.Test;
import per.wsk.java.study01.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *  鉴于Java中数组用来存储数据的局限性，我们通常使用List替代数组
 *  List集合类中元素有序、且可重复，集合中的每个元素都有其对应的顺序索引。
 *  List容器中的元素都对应一个整数型的序号记载其在容器中的位置，可以根据
 * 序号存取容器中的元素。
 *  JDK API中List接口的实现类常用的有：ArrayList、LinkedList和Vector。
 *
 *
 *
 * 1. List接口框架
 *
 *
 *    |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----List接口：存储有序的、可重复的数据。  -->“动态”数组,替换原有的数组
 *              |----ArrayList：作为List接口的主要实现类；线程不安全的，效率高；
 *                              底层使用Object[] elementData存储
 *              |----LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；
 *                               底层使用双向链表存储
 *              |----Vector：作为List接口的古老实现类；线程安全的，效率低；
 *                              底层使用Object[] elementData存储
 *
 *
 * 2. ArrayList的源码分析：
 *   2.1 jdk 7情况下
 *      ArrayList list = new ArrayList();//构造器底层创建了长度是10的Object[]数组elementData
 *      list.add(123);//elementData[0] = new Integer(123);
 *      ...
 *      list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
 *      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *      结论：建议开发中使用带参的构造器：ArrayList list = new ArrayList(int capacity)
 *
 *   2.2 jdk 8中ArrayList的变化：
 *      ArrayList list = new ArrayList();//底层Object[] elementData初始化为{}.并没有创建长度为10的数组
 *
 *      list.add(123);//第一次调用add()时，底层才创建了长度10的数组，并将数据123添加到elementData[0]
 *      ...
 *      后续的添加和扩容操作与jdk 7 无异。
 *   2.3 小结：jdk7中的ArrayList的对象的创建类似于单例的饿汉式，空参构造器中直接创建一个初始容量为10的数组。
 *             jdk8中的ArrayList的对象的创建类似于单例的懒汉式，空参构造器中创建一个长度为0的数组，
 *             当添加第一个元素时再创建一个始容量为10的数组延迟了数组的创建，节省内存。
 *
 *  3. LinkedList的源码分析：
 *      LinkedList list = new LinkedList(); 底层是双链表，内部声明了Node类型的first和last属性，默认值为null
 *      list.add(123);//将123封装到Node中，创建了Node对象。
 *
 *      其中，Node定义为：体现了LinkedList的双向链表的说法
 *      private static class Node<E> {
             E item;
             Node<E> next;
             Node<E> prev;

             Node(Node<E> prev, E element, Node<E> next) {
             this.item = element;
             this.next = next;
             this.prev = prev;
             }
         }
 *
 *   4. Vector的源码分析：jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组。
 *      在扩容方面，默认扩容为原来的数组长度的2倍。
 *
 *      Vector与ArrayList基本类似，区别是：
 *          ①Vector是线程安全的
 *          ②底层数组长度的扩容与ArrayList有些不同。
 *
 *      Vector现在基本不使用了，如果出现多个线程操作同一个集合对象的问题，一般不通过Vector解决，
 *      一般都通过Collections解决，Collections类后面会讲到。
 *
 *      另外，Vector有一个子类Stack，
 *      Stack这个类底层是数据结构中的栈结构，先进后出，有压栈、出栈等操作。
 *
 *
 *  面试题：ArrayList、LinkedList、Vector三者的异同？
 *  同：三个类都是实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 *  不同：见上
 *
 *
 *   5. List接口中的常用方法
 *
 * @author shkstart
 * @create 2019 上午 11:39
 */
public class ListTest {

    /*
    void add(int index, Object ele):在index位置插入ele元素
    boolean addAll(int index, Collection eles):从当前集合的index位置开始将eles中的所有元素添加进来
    Object get(int index):获取指定index位置的元素
    int indexOf(Object obj):返回obj在集合中首次出现的位置
    int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
    Object remove(int index):移除指定index位置的元素，并返回此元素
    Object set(int index, Object ele):设置指定index位置的元素为ele
    List subList(int fromIndex, int toIndex):返回当前集合对象从fromIndex到toIndex位置的子集合（包前不包后）

    总结：常用方法
    增：add(Object obj)
    删：remove(int index) / remove(Object obj)
    改：set(int index, Object ele)
    查：get(int index)
    插：add(int index, Object ele)
    长度：size()
    遍历：① Iterator迭代器方式
         ② 增强for循环
         ③ 普通的循环

     */
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        System.out.println(list);

        //void add(int index, Object ele):在index位置插入ele元素
        //index及index后面的元素都向后移动一位，详情看源码
        list.add(1,"BB");
        System.out.println(list);

        //运行时此行报错 下标为6,7的位置没有任何数据，直接添加下标为8位置的数据，会出错
        //原因详见源码
//        list.add(8,"hello");

        //boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
        //addAll 方法返回boolean类型，添加成功返回true，失败返回false。
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);
        //list.add(list1)和list.addAll(list1)表达的意思不同
        //list.addAll(list1)表示把list1中的所有元素添加到list中
        //list.add(list1)表示把list1这个集合当成一个元素，添加到list中，即list1这个集合
        //是list中的一个元素
        System.out.println(list.size());//9

        //Object get(int index):获取指定index位置的元素
        System.out.println(list.get(0));

    }

    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);
        //int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1.
        int index = list.indexOf(4567);
        System.out.println(index);

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置。如果不存在，返回-1.
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index):移除指定index位置的元素，并返回此元素
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);
        // Collection类中的  remove(Object obj) 方法
        //如果集合中某个索引位置存储的是一个数字，要删除这个数字，需要把这个数字变为Integer类型
        //才能删除，因为如果是int类型，会调用remove(int index)方法，这样就变成了
        // 删除的是索引是这个数字的元素

        //remove(Object obj)方法   删除时要求obj这个对象所在的类重写了equals()方法，因为删除时要先在集合中
        //查找obj这个元素，查找的规则就是根据equals()查找的，如果集合中没有这个元素，就什么也没操作，如果有
        //就从集合中删除这个元素，如果这个元素在集合中出现多次，删除下标最靠前的那个。详细原因看源码。
        list.remove(new Person("Tom",14));
        System.out.println(list);

        //Object set(int index, Object ele):设置指定index位置的元素为ele，并把之前index索引处
        //的元素返回。
        list.set(1,"CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的左闭右开区间的子集合
        //这个方法并不改变原来的集合中的元素。
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);


    }


    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("***************");

        //方式二：增强for循环
        for(Object obj : list){
            System.out.println(obj);
        }

        System.out.println("***************");

        //方式三：普通for循环
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }

    }


    @Test
    public void test4(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

//        list.remove(4);//IndexOutOfBoundsException

//        list.add(5,"abcd");//IndexOutOfBoundsException

//        list.remove(3);//IndexOutOfBoundsException

//        list.set(3,"bcde");//IndexOutOfBoundsException
    }


    /*
    区分List中remove(int index)和remove(Object obj)
     */
    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//
    }

    private void updateList(List list) {
        list.remove(2);
//        list.remove(new Integer(2));
//        list.remove(Integer.valueOf(2));
    }
}
