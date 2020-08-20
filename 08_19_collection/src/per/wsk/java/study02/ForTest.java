package per.wsk.java.study02;

import org.junit.Test;
import per.wsk.java.study01.Person;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk 5.0 新增了foreach循环，用于遍历集合、数组
 *
 * @author shkstart
 * @create 2019 上午 11:24
 */
public class ForTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素的类型 局部变量 : 集合对象)
        //内部仍然调用了迭代器。详细原理可以通过debug看下代码的整个执行流程。
        for(Object obj : coll){
            System.out.println(obj);
        }

        coll.clear();
        //由迭代器的原理可知,先调用hasNext()方法，如果有下一个元素再调用next()方法
        //所以，即便coll这个集合里面一个元素都没有，下面for each循环不会报错
        //但如果coll是null，就会报错
        for (Object obj : coll) {
            System.out.println(obj);
        }
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        //for(数组元素的类型 局部变量 : 数组对象)
        for(int i : arr){
            System.out.println(i);
        }
    }

    //练习题
    @Test
    public void test3(){
        String[] arr = new String[]{"MM","MM","MM"};

//        //方式一：普通for赋值
//        for(int i = 0;i < arr.length;i++){
//            arr[i] = "GG";
//        }

        //方式二：增强for循环
        for(String s : arr){
            s = "GG";
        }

        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }
        //通过方式一的遍历后，数组的三个元素的值都发生了变化，
        //通过方式二遍历，数组的三个元素的值都没有变化，因为方式二是for each循环
        //每次循环都信定义了一个变量s，在循环中是把变量s的值改变了，数组中每个
        //元素的值没有改变。

    }
}
