package per.wsk.java.study01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，使用迭代器Iterator接口
 *  Collection接口继承了Iterable接口，Iterable中有一个Iterabor类型的属性。
 *
 * 关于迭代器的底层原理详见印象笔记和尚硅谷的课件
 *
 *
 * 1.内部的方法：hasNext() 和  next()
 * 2.集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
 *   默认游标在集合的第一个元素之前。
 * 3.内部定义了remove(),可以在遍历的时候，删除集合中的元素。
 *   此方法不同于集合直接调用remove()
 *
 * @author shkstart
 * @create 2019 上午 10:44
 */
public class IteratorTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();
        //方式一：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        //报异常：NoSuchElementException
//        System.out.println(iterator.next());

        //方式二：一般不这样写
//        for(int i = 0;i < coll.size();i++){
//            System.out.println(iterator.next());
//        }

        //方式三：推荐
        ////hasNext():判断是否还有下一个元素
        while(iterator.hasNext()){
            //next():①指针下移 ②将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //错误方式一：下面的错误while的循环条件和循环体都调用了next()方法，这样集合
        //中的元素会隔着输出，而且最后会出现NoSuchElementException
//        Iterator iterator = coll.iterator();
//        while((iterator.next()) != null){
//            System.out.println(iterator.next());
//        }

        //错误方式二：
        //集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
        // 默认游标都在集合的第一个元素之前。  这样会出现死循环。
        /*while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }*/


    }

    //测试Iterator中的remove()
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //删除集合中"Tom"
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            //如果还未调用next()或在上一次调用 next 方法之后已经调用了 remove 方法，
            // 再调用remove都会报IllegalStateException。  因为游标指向的元素不能为空，当删除一次后不能
            //再删除。
//            iterator.remove();
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
//                iterator.remove();
            }

        }
        //遍历集合
        iterator = coll.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    @Test
    public void test04(){
        ArrayList list = new ArrayList();
        list.add("abc");
        list.add(345);
        list.add(false);
        list.add(new int[]{2,7,17});

        System.out.println("集合长度是: "+list.size());//集合长度是: 4
        System.out.println(list.get(0));//abc

        list.remove(0);

        System.out.println("集合长度是: "+list.size());//集合长度是: 3
        System.out.println(list.get(0));//345

        //上面说明，集合remove方法，删除第n个元素后，从第n+1个元素开始，每个元素的
        //下标都会向前移动一个位置。

        /*
        删除时可以采用迭代器的方式删除
         */
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            //iterator的指针向下移动一位
            iterator.next();
            //删除iterator的指针指向的当前索引的元素
            iterator.remove();
        }

        System.out.println("集合长度是: "+list.size());//集合长度是: 0
    }

    /*
    注意：迭代器不是装载元素的容器，集合才是。
          迭代器的删除方法可以删除集合中的元素。
          迭代器中可以向一个集合提供遍历和删除操作，但新增、修改的功能迭代器不能提供。
     */

}
