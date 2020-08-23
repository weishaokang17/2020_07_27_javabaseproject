package per.wsk.java.study01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author weishaokang
 * @date 2020-08-04 20:54
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 *
 *  Collection接口中的方法的使用

    1、添加
     add(Object obj)
     addAll(Collection coll)

    2、获取有效元素的个数
     int size()

    3、清空集合
     void clear()

    4、是否是空集合
     boolean isEmpty()

    5、是否包含某个元素
     boolean contains(Object obj)：是通过元素的equals方法来判断是否
    是同一个对象
     boolean containsAll(Collection c)：也是调用元素的equals方法来比
    较的。拿两个集合的元素挨个比较

    6、删除
     boolean remove(Object obj) ：通过元素的equals方法判断是否是
    要删除的那个元素。只会删除找到的第一个元素
     boolean removeAll(Collection coll)：取当前集合的差集

    7、取两个集合的交集
     boolean retainAll(Collection c)：把交集的结果存在当前集合中，不
    影响c

    8、集合是否相等
     boolean equals(Object obj)

    9、转成对象数组
     Object[] toArray()

    10、获取集合对象的哈希值
     hashCode()

    11、遍历
     iterator()：返回迭代器对象，用于集合遍历

 * 结论：
 * 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals().
 *
 */
public class CollectionTest2 {


    @Test
    public void test1(){
        Collection coll = new ArrayList();

        //1. add(Object e):将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱  其实是coll.add(new Integer(123))
        coll.add(new Date());

        //2. size():获取添加的元素的个数
        System.out.println(coll.size());//4

        //addAll(Collection coll1):将coll1集合中的所有元素都添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll1.add(123);//此时的123仍会添加进去，虽然coll里面已经有了123，但因为arrayList可以添加重复元素
        coll.addAll(coll1);

        System.out.println(coll.size());//7
        System.out.println(coll);

        //3. clear():清空集合元素
        coll.clear();

        //4. isEmpty():判断当前集合是否为空  为空返回true,不是空返回false
        System.out.println(coll.isEmpty());

    }


    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
//        Person p = new Person("Jerry",20);
//        coll.add(p);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //5 .contains(Object obj):判断当前集合中是否包含obj 包含返回true,不包含返回false

        boolean contains = coll.contains(123);
        System.out.println(contains);
        //在判断是否包含当前对象时，会调用obj对象所在类的equals()方法。
        // (当前集合对象是arrayList，详见arrayList的源码)
        //所以下面这行虽然是新建了一个String对象，和coll中值是Tom的字符串不是同一个String对象，
        //但下面这行返回的是true,不是false，因为String重写了Object中的equals()方法。
        System.out.println(coll.contains(new String("Tom")));

        //因为arrayList的contains方法比较的是equals(),所以下面这行是新建了一个Person对象，
        //和coll中已有的Person对象虽然两个属性都相同，但两个对象地址不同，
        //如果Person没有重写equals()方法，会调用Person父类（即Object）的equals()方法,object类的
        //equals()方法比较的是地址。所以，下面这行输出false还是true,要看person对象调用的是哪个
        //equals()方法。
        System.out.println(coll.contains(new Person("Jerry",20)));//false -->true

        //6. containsAll(Collection coll1):判断形参coll1中的所有元素是否都存在于当前集合中。
        //全部存在才返回true,只要有一个不存在就返回false,
        // 详见源码(arrayList没有containsAll这个方法，arrayList继承的父类中有这个方法，里面用的
        // 仍是equals(),不是 == )
        Collection coll1 = Arrays.asList(123,4567);
        System.out.println(coll.containsAll(coll1));
    }

    @Test
    public void test3(){
        //7.remove(Object obj):从当前集合中移除obj元素。
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        coll.remove(123);//删除成功返回true，删除失败返回false，删除失败其实就是意味着
        //集合中没有这个元素，删除时要先判断有没有这个元素，查找有没有这个元素时仍是按照equals()方法判断的。
        System.out.println(coll);

        //下面这行要想确保删除成功，需要保证Person重写了equals()方法。
        coll.remove(new Person("Jerry",20));
        System.out.println(coll);

        //8. removeAll(Collection coll1): 删除两个集合的交集 （仍是用的equals()方法）。
        Collection coll1 = Arrays.asList(123,456,789);
        boolean b = coll.removeAll(coll1);
        System.out.println(b);
        System.out.println(coll);


    }

    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //9.retainAll(Collection coll1):交集：获取两个集合的交集，并把交集赋值给当前集合
        // （求交集时仍是用的equals()）
//        Collection coll1 = Arrays.asList(123,456,789);
//        coll.retainAll(coll1);
//        System.out.println(coll);

        //10.equals(Object obj): 比较两个集合是否相同
        // 要想返回true，需要当前集合和形参集合的元素都相同，且顺序都相同，因为下面是ArrayList对象，
        // ArrayList是有序的。
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add(123);
        coll1.add(new Person("Jerry",20));
        coll1.add(new String("Tom"));
        coll1.add(false);
        //下面这行返回false，可以看下源码，因为arrayList是有序集合，是都拿两个集合的第一个
        //元素用equals()比较，相同的话再比较第二个，依此类推。
        System.out.println(coll.equals(coll1));


    }

    @Test
    public void test5(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //11.hashCode():返回当前对象的哈希值
        System.out.println(coll.hashCode());

        //12.集合 转为数组：toArray()
        Object[] arr = coll.toArray();
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }

        //拓展：数组 --->集合:调用Arrays类的静态方法asList()
        //asList()括号中的参数列表是可变形参，之前讲解过，参数是可变形参和数组类型的参数一模一样。
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);

        List arr1 = Arrays.asList(new int[]{123, 456});
        System.out.println(arr1.size());//1  此时把new int[]{123, 456}整个数组当成了一个元素。

        List arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());//2

        //上面几行代码可以得出结论：8种基本数据类型的数组转集合需要先把数组类型自动装箱，再转集合。

        //9.iterator():返回Iterator接口的实例，用于遍历集合元素。放在IteratorTest.java中测试
        //iterator放到后面讲解
    }



}
