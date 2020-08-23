package per.wsk.java.study05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Collections:操作Collection、Map的工具类
 *
 * 因为java中的集合是Collection和Map两个框架，
 * 意味着Collections可以操作java中的所有集合。
 *
 *
 * 面试题：Collection 和 Collections的区别？
 *
 *
 * @author shkstart
 * @create 2019 下午 4:19
 */
public class CollectionsTest {

/*
reverse(List)：反转 List 中元素的顺序
shuffle(List)：对 List 集合元素进行随机排序
            （同一个list对象，多次调用这个方法，每次调用返回的结果可能不同，
            每次调用都是随机的）

sort(List)：根据元素的自然顺序对指定 List 集合中的元素按升序排序
            (调用这个方法需要保证list中的集合中的所有元素都是同一个对象，且这个对象可以进行
            自然排序或定制排序)

sort(List，Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序

swap(List，int i， int j)：将指定 list 集合中的 i 处元素和 j 处元素进行交换

Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
                    (调用这个方法需要保证list中的集合中的所有元素都是同一个对象，
                    且这个对象可以进行自然排序)
Object min(Collection): 和上面的max方法几乎一样，唯一的区别是上面的返回集合中的最大元素，
                      这个方法是返回集合中的最小的元素。

Object max(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素
Object min(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最小元素

int frequency(Collection，Object)：返回指定集合中指定元素的出现次数

void copy(List dest,List src)：将src中的内容复制到dest中

boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值
（即list对象中的所有元素值是oldVal的，全部替换为newVal）
 */


    @Test
    public void test1(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(765);
        list.add(765);
        list.add(-97);
        list.add(0);

        System.out.println(list);//[123, 43, 765, 765, 765, -97, 0]

    //        Collections.reverse(list);
    //        Collections.shuffle(list);
    //        Collections.sort(list);
    //        Collections.swap(list,1,2);
        int frequency = Collections.frequency(list, 123);

        System.out.println(frequency);//输出元素 123在集合list中出现的次数。


        list.add("abcd");
        //此时集合中既有Integer类型的元素又有String类型的元素，不可以用自然排序了。
        //但仍可以用定制排序，是否可以用定制排序取决于定制排序中compare方法的实现
        Comparator com = new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    Integer int1 = (Integer) o1;
                    Integer int2 = (Integer) o2;
                    return int1.compareTo(int2);
                } else if (o1 instanceof Integer && o2 instanceof String){
                    return -1;
                } else if (o2 instanceof Integer && o1 instanceof String) {
                    return 1;
                } else {
                    throw new RuntimeException("参数类型错误");
                }
            }
        };
        //下面这行是自然排序，这行运行会报错，因为集合list中既有Integer又有String，不能调用
        //自然排序。
//        Collections.sort(list);

        //定制排序
        Collections.sort(list,com);
        System.out.println(list);
        //  [-97, 0, 43, 123, 765, 765, 765, abcd]
    }



    @Test
    public void test2(){
        /*
        void copy(List dest,List src): 将src中的内容复制到dest中
        看下源码就会发现：前提条件是dest的size必须大于等于src的size，否则会报
        IndexOutOfBoundsException
         */
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        //报异常：IndexOutOfBoundsException("Source does not fit in dest")
//        List dest = new ArrayList();
//        Collections.copy(dest,list);
        //正确的：
        /*
        下面这行代码是使得dest中保存的元素个数 与 list中保存的元素个数相等。
        这样才能调用Collections的copy()方法。
        此外，dest的size如果大于list的size，例如list的size是a,dest的size是b。
        此时dest的索引0至a-1的所有元素被替换为list的索引0至a-1处。
        dest的索引a至索引b-1处的所有元素仍保持不变。
         */
        List dest = Arrays.asList(new Object[list.size()]);
        System.out.println(dest.size());//list.size();
        Collections.copy(dest,list);

        System.out.println(dest);


        System.out.println("-------------------------");

        /*
        Collections 类中提供了多个 synchronizedXxx() 方法，
        该方法可使将指定集合包装成线程同步的集合，从而可以解决
        多线程并发访问集合时的线程安全问题

         */
        //返回的list1即为线程安全的List
        List list1 = Collections.synchronizedList(list);
        System.out.println(list1);//返回一个新的线程安全的集合，
        // 而且这个返回的新集合包含之前list中的元素。

        System.out.println(list1.getClass().getName());
        //此时list1的所属类是： java.util.Collections$SynchronizedRandomAccessList

        /*
        Collections中有好多这种同步方法，如
        synchronizedCollection，
        synchronizedMap，
        synchronizedSet，
        synchronizedSortedSet，
        synchronizedSortedMap等。
         */
    }

}
