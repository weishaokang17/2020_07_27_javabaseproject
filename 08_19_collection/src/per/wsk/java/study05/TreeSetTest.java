package per.wsk.java.study05;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author shkstart
 * @create 2019 下午 4:59
 */
public class TreeSetTest {

    /*
    TreeSet的底层原理：
    一、TreeSet与HashSet和LinkedHashSet一样，底层也实现了Set接口，
    不同的是，HashSet和LinkedHashSet是直接实现了Set接口，TreeSet是实现了Set接口的子接口。
    等于是TreeSet间接实现了Set接口。

    
    二、TreeSet底层用的是红黑树的数据结构。
    
    三、 TreeSet本质是是排序的set集合，TreeSet在添加元素时，TreeSet只能添加相同类的对象，
    也就是说TreeSet中的所有元素必须都是同一个类的对象（子父类的关系也可以）,而且向
   TreeSet中添加的元素所属的类，这个类必须可以自然排序或定制排序，（即必须实现Comparable接口
   或者必须可以通过Comparator来定制排序）。

    四、TreeSet添加元素的底层原理是：添加时，用当前元素与TreeSet对象中的已有元素进行
   排序比较（具体看这个类是定制排序还是自然排序），如果排序返回的结果是1或-1，就添加成功，
   如果返回0，就添加失败。
   因为TreeSet底层是红黑树，排序比较的顺序是先拿当前要添加的元素与TreeSet红黑树的根结点
   进行排序比较，如果返回-1就拿根结点的左子结点再与当前元素进行排序比较，如果返回
   1就拿根结点的右子结点再与当前元素进行排序比较，依此类推，如果中间有返回0的，就结束排序比较，
   此时添加失败。

    五、因为只有相同类的两个实例才会比较大小，所以向 TreeSet 中添加的应该是同
    一个类的对象。

    六、结合TreeSet的原理，不难得知：
    当需要把一个对象放入 TreeSet 中，重写该对象对应的 equals() 方法时，应保
    证该方法与 compareTo(Object obj) 方法有一致的结果：如果两个对象通过
    equals() 方法比较返回 true，则通过 compareTo(Object obj) 方法比较应返回 0。
    否则，让人难以理解
    */

    /*
    1.向TreeSet中添加的数据，要求是相同类的对象。
    2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）
    3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0.不再是equals().
    4.定制排序中，比较两个对象是否相同的标准为：compare()返回0.不再是equals().
     */
    @Test
    public void test1(){
        TreeSet set = new TreeSet();

        //下面注释掉的几行运行报错：不能添加不同类的对象，向TreeSet中添加的数据，
        // 要求是相同类的对象，也就是说，当向TreeSet添加第一个元素时，就已经确定了这个TreeSet对象
        //只能添加哪个类的元素。
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom",12));

            //举例一：
//        set.add(34);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);

        //举例二：
        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else{
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        TreeSet set = new TreeSet(com);
        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Mary",33));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
