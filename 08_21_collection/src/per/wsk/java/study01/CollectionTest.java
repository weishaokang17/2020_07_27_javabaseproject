package per.wsk.java.study01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author shkstart
 * @create 2019 上午 9:36
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(343);
        coll.add(343);

        //jdk8中Iterable接口新增了默认方法forEach，括号中是lamda表达式
        coll.forEach(System.out::println);
    }


    //练习：在List内去除重复数字值，要求尽量简单

    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        //通过addAll,把list的每一个元素都添加到set中，
        // 添加到set时，会根据set的添加元素的底层原理添加，这样list重复的数字只能添加到set中一次。
        set.addAll(list);
        return new ArrayList(set);
    }
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object integer : list2) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);
        //[Person{name='BB', age=1002}, Person{name='AA', age=1001}]

        p1.name = "CC";
        set.remove(p1);
        /*
        上面一行set中删除p1这个对象时，是按照p1的hash值，计算出在set中应该放的位置，
        再去set中这个位置查找元素，用equals()方法查找，把set中这个位置的链表中equals()方法
        返回true的元素删除掉。
        因为p1这个对象添加到set中时，p1的name属性是AA，因hash值的计算与属性值有关，故假设p1
        添加到的set的位置是m，要删除p1时，此时p1的name属性是CC，此时根据hash值去set集合中删除p1,
        因为p1的name属性改变了所以计算的hash值也改变了，导致删除时的计算的p1在set的位置大概率不是p1
        实际添加的位置，这样删除失败了。所以上面set.remove(p1)大概率删除失败。
        */
        System.out.println(set);
        //[Person{name='BB', age=1002}, Person{name='CC', age=1001}]

        //下面这行会添加成功，因为添加时计算的hash值是按照name属性值是CC计算的。
        set.add(new Person(1001,"CC"));
        System.out.println(set);
        //[Person{name='BB', age=1002}, Person{name='CC', age=1001}, Person{name='CC', age=1001}]

        /*
        下面这行也会添加成功，计算的添加到set中的位置肯定是和上面p1对象添加到set中的位置相同，
        此时就调用equals()方法，因为下面的Person对象的name属性是AA，p1的name属性是CC，所以
        equals()方法返回false,这样会添加成功，下面的Person对象和p1对象在set中的数组位置相同，两个
        对象以链表的方式连接。
         */
        set.add(new Person(1001,"AA"));
        System.out.println(set);
        //[Person{name='BB', age=1002}, Person{name='CC', age=1001}, Person{name='CC', age=1001}, Person{name='AA', age=1001}]
    }


}
