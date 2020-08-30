package per.wsk.java.study01;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author shkstart
 * @create 2019 上午 10:38
 *
 * 加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个
 * 类只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可
 * 以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看
 * 到类的结构，所以，我们形象的称之为：反射。
 *
 *
 *  对象照镜子后可以得到的信息：某个类的属性、方法和构造器、某个类到底实现了哪些接
 * 口。对于每个类而言，JRE 都为其保留一个不变的 Class 类型的对象。一个 Class 对象包含
 * 了特定某个结构(class/interface/enum/annotation/primitive type/void/[])的有关信息。
 *  Class本身也是一个类
 *  Class 对象只能由系统建立对象
 *  一个加载的类在 JVM 中只会有一个Class实例
 *  一个Class对象对应的是一个加载到JVM中的一个.class文件
 *  每个类的实例都会记得自己是由哪个 Class 实例所生成
 *  通过Class可以完整地得到一个类中的所有被加载的结构
 *  Class类是Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的
 * Class对象
 *
 *
 * Class类中的一些常用方法
 *
 * 方法名 功能说明
 * static Class forName(String name) 返回指定类名 name 的 Class 对象
 * Object newInstance() 调用缺省构造函数，返回该Class对象的一个实例
 * getName() 返回此Class对象所表示的实体（类、接口、数组类、基本类型或void）名称
 * Class getSuperClass() 返回当前Class对象的父类的Class对象
 * Class [] getInterfaces() 获取当前Class对象的接口
 * ClassLoader getClassLoader() 返回该类的类加载器
 * Constructor[] getConstructors() 返回一个包含某些Constructor对象的数组
 * Field[] getDeclaredFields() 返回Field对象的一个数组
 * Method getMethod(String name,Class … paramTypes)  返回一个Method对象，此对象的形参类型为paramType
 *
 *
 */
public class ReflectionTest {


    //反射之前，对于Person的操作
    @Test
    public void test1() {
        //1.创建Person类的对象
        Person p1 = new Person("Tom", 12);

        //2.通过对象，调用其内部的属性、方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //比如：name、showNation()以及私有的构造器
    }

    //反射之后，对于Person的操作
    @Test
    public void test2() throws Exception{
        Class clazz = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p);

        //2.通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("*******************************");

        //通过反射，可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1,"中国");//相当于String nation = p1.showNation("中国")
        System.out.println(nation);

    }

    //疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
    //建议：直接new的方式。
    //什么时候会使用：反射的方式。 反射的特征：动态性
    //疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
    //不矛盾。

    /*
    关于java.lang.Class类的理解
    1.类的加载过程：
    程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
    接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件
    加载到内存中。此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此
    运行时类，就作为Class的一个实例。

    2.换句话说，Class的一个实例就对应着一个运行时的类。
    3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式
    来获取此运行时类。
     */
    //获取Class的实例的方式（前三种方式需要掌握）
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通过运行时类的对象,调用getClass(), getClass()是Object类中的方法。
//        Person p1 = new Person();
//        Class clazz2 = p1.getClass();
//        System.out.println(clazz2);

        //方式三：调用Class的静态方法：forName(String classPath)
//        Class clazz3 = Class.forName("Person");
        //必须写Person类的全路径。
        Class clazz3 = Class.forName("per.wsk.java.study01.Person");
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

//        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四：使用类的加载器：ClassLoader  (了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("per.wsk.java.study01.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);

    }


    //万事万物皆对象？对象.xxx,File,URL,反射,前端、数据库操作


    //Class实例可以是哪些结构的说明：
    @Test
    public void test4(){
        // 内部类、匿名类等都有Class实例

        Class c1 = Object.class;
        Class c2 = Comparable.class;//接口
        Class c3 = String[].class;//数组
        Class c4 = int[][].class;
        Class c5 = ElementType.class;//枚举
        Class c6 = Override.class;//注解
        Class c7 = int.class;//基本数据类型
        Class c8 = void.class;//没有返回值
        Class c9 = Class.class;//Class本身 也是Class的一个实例

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class，下面的结果是true
        System.out.println(c10 == c11);

    }
}
