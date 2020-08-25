package per.wsk.java.study02;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类
 * @author shkstart
 * @create 2019 上午 11:05
 */
public class Order<T> {

    //下面这行报错，因为静态属性a是随着类的加载而加载的，在加载时就要确定具体哪种结构，
    //所以静态属性不能用泛型。
//    static T a;

    String orderName;
    int orderId;

    //类的内部结构就可以使用类的泛型，例如下面的orderT这个属性

    T orderT;//该类名后面加上<T>，意思就是新建Order对象时，要指定T是什么类型（T表示 类或接口 的类型参数，
    // T是type的意思，当然这个参数名字随便起，可以不叫T。T只能是类或接口，不能用基本数据类型填充
    // 如果新建Order对象时，例如指明新建的Order对象后面的T是Integer,
    // 那么类中orderT这个属性的类型就是Integer。  如果新建Order对象时，没有指明T的类型，
    // 那么orderT是Object类型）

    public Order(){
        //编译不通过,因为直接new的类必须是具体的类。
//        T[] arr = new T[10];
        //编译通过
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName,int orderId,T orderT){
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT(){
        return orderT;
    }

    public void setOrderT(T orderT){
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    //非静态方法中可以使用类的泛型，但静态方法中不能使用类的泛型。
//    public static void show(T orderT){
//        System.out.println(orderT);
//    }

    //不能在try-catch中使用泛型定义
    public void show(){
        //编译不通过
//        try{
//
//
//        }catch(T t){
//
//        }

    }

    //泛型方法：在方法中出现了泛型的结构的方法叫泛型方法。

    //泛型参数与类的泛型参数没有任何关系，并不是泛型方法一定要位于泛型类中。

    /*
    注意：不是出现了泛型就一定是泛型方法,例如下面的method这个方法，不是泛型方法。只不过是
    一个非静态方法，参数用的是类的指定的泛型。 但这个方法不是泛型方法。

    泛型方法格式是：在方法的修饰符和方法的返回值类型中间 要加上<K>
    其中，K只是参数名字，也可以把K换成其他的英文字母或符号。

    泛型方法的格式：
        [访问权限] <泛型> 返回类型 方法名([泛型标识 参数名称]) 抛出的异常
     */
    public void method(T arg){

    }
    //下面的method1这个方法，如果去掉<K>，会报错，因为去掉<K>后编译器为会让K是一个类或接口，
    // 但编译器找不到K这个类或接口，就报错，
    public <K> void method1(K arg){

    }


    //泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在实例化类时确定。
    public static <E>  List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }


}
