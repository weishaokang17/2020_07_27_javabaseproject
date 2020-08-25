package per.wsk.java.study05;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 1. 泛型在继承方面的体现
 *
 *
 * 2. 通配符的使用
 *
 * @author shkstart
 * @create 2019 下午 2:13
 */
public class GenericTest {

    /*
    1. 泛型在继承方面的体现
       虽然类A是类B的父类，但是G<A>和G<B>二者不具备子父类关系，二者是并列关系。
       补充：类A是类B的父类，此时 A<G> 是 B<G> 的父类
     */
    @Test
    public void test1(){
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;
        //编译不通过
//        Date date = new Date();
//        str = date;
        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        //此时的list1和list2的类型不具有子父类关系
        //编译不通过
//        list1 = list2;
        /*
        反证法：
        假设list1 = list2;
           list1.add(123);导致混入非String的数据。出错。

         */
        show(list1);
        show1(list2);

    }


    public void show1(List<String> list){

    }

    public void show(List<Object> list){

    }

    @Test
    public void test2(){
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;
        //此时三个list对象可以互相赋值，因为AbstractList、List、ArrayList
        //是子父类或接口实现类的关系，而且泛型都是String类。
        List<String> list4 = new ArrayList<>();
    }

    // 看下下面注释掉的这个方法中的代码
    /*
    public void testGenericAndSubClass() {
        Person[] persons = null;
        Man[] mans = null;
// 而 Person[] 是 Man[] 的父类.
        persons = mans;
        Person p = mans[0];
// 在泛型的集合上
        List<Person> personList = null;
        List<Man> manList = null;
// personList = manList;(报错)
    }
     */




    /*
    2. 通配符的使用
       通配符：?

    ①使用类型通配符：？
    比如：List<?> ，Map<?,?>
    List<?>是List<String>、List<Object>等各种泛型List的父类。

    ②读取List<?>的对象list中的元素时，永远是安全的，因为不管list的真实类型
    是什么，它包含的都是Object。

       类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;
        //下面两行编译通过，因为List<?>是List<String>和List<Object>的父类,
        //但如果把print方法中的参数改成List<Object>类型，下面的第二行会报错，因为
        //List<String>类型不是List<Object>的子类
        print(list1);
        print(list2);

        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加、修改和删除：对于List<?>就不能向其内部添加数据或修改数据（除了可以添加null和
        // 把元素修改为null之外）。  但List<?>可以删除数据，无论是根据下标删除还是根据元素删除。
//        list.add("DD");
//        list.add('?');
//        list.set(0,"a");

        list.add(null);

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }


    public void print(List<?> list){
//    public void print(List<Object> list){

        if (list == null){
            return;
        }

        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            //接收List<?>中的元素时，用Object可以接收，但List<String>不是List<Object>的子类
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }



    /*
    3.有限制条件的通配符的使用。
        ? extends A:
                G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类
        此时的?表示 A类以及A类的直接子类或间接子类

        ? super A:
                G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
        此时的?表示 A类以及A类的直接父类或间接父类
     */
    @Test
    public void test4(){

        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
        //下面注释掉的这行编译报错
//        list1 = list5;


        list2 = list4;
        list2 = list5;
        //下面注释掉的这行编译报错
//        list2 = list3;

        //读取数据：
        list1 = list3;
        //因为list1的所有数据都是Person类或Person的子类
        //所以，此时接收list1的数据时，可以用Person类或Person类的父类接收。
        Person p = list1.get(0);

        list2 = list4;
        //因为list2的所有数据都是Person类或Person的父类
        //所以，此时接收list2的数据时，只能用Object类接收。
        Object obj = list2.get(0);

        //list1的添加、修改和删除：对于list1就不能向其内部添加数据或修改数据（除了可以添加null和
        //  把元素修改为null之外）。  但list1可以删除数据，无论是根据下标删除还是根据元素删除。

        //list2的添加、修改和删除：对于list2就可以向其内部添加Person类对象或Person类的子类对象，
        //  或修改数据（修改为Person类对象或Person类的子类对象），当然也可以添加或修改成null。
        //  list2也可以删除数据，无论是根据下标删除还是根据元素删除。

        //编译通过
        list2.add(new Person());
        list2.add(new Student());

    }


    /*
    泛型通配符的使用注意点：
    //注意点1：编译错误：不能用在泛型方法声明上，返回值类型前面<>不能使用?
    public static <?> void test(ArrayList<?> list){
    }
    //注意点2：编译错误：不能用在泛型类的声明上
    class GenericTypeClass<?>{
    }
    //注意点3：编译错误：不能用在创建对象上，右边属于创建集合对象,即下面的代码，
    = 左边的通配符没有错，但=右边的通配符错误。
    ArrayList<?> list2 = new ArrayList<?>();
     */

}
