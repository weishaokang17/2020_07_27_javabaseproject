package per.wsk.java.study01;

import java.io.Serializable;

/**
 * Person需要满足如下的要求，方可序列化
 * 1.需要实现接口：Serializable
 * 2.当前类提供一个全局常量：serialVersionUID
     凡是实现Serializable接口的类都有一个表示序列化版本标识符的静态变量：
     ①private static final long serialVersionUID;
     ②serialVersionUID用来表明类的不同版本间的兼容性。简言之，其目的是以序列化对象
     进行版本控制，有关各版本反序列化时是否兼容。
     ③如果类没有显示定义这个静态常量，这个类因为实现了Serializable接口，因此程序运行时
     会自动给这个类指定一个serialVersionUID的值，如果这个类的实例被序列化了，此时若这个
     类中内容被修改，那么程序会把这个类的serialVersionUID的值也做修改，但由于被序列化的
     实例中存的是旧的serialVersionUID，这样导致被序列化的实例在反序列化时由于找不到旧的
     匹配不到旧的serialVersionUID报错。  如果显示声明了serialVersionUID，那么这个类内容
     被修改后但serialVersionUID值仍不变，所以不会出现这个问题。
     ④建议serialVersionUID这个类显示声明出来，而且不同的类这个值最好不要相同，
        IDEA中自动生成serialVersionUID的设置是：
    file→setting→Inspections→Serialization issues→
    Serialization class without 'serialVersionUID' 打上勾
        serialVersionUID 的自动生成是根据这个类、包以及类属性等进行复杂的算法生成的。


 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性
 *   也必须是可序列化的。
 *
 * 4.简单来说，Java的序列化机制是通过在运行时判断类的serialVersionUID来验
 * 证版本一致性的。在进行反序列化时，JVM会把传来的字节流中的
 * serialVersionUID与本地相应实体类的serialVersionUID进行比较，如果相同
 * 就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异
 * 常。(InvalidCastException)
 *
 *
 * 补充：ObjectOutputStream和ObjectInputStream不能序列化
 *      static和transient修饰的成员变量，
 *      被static和transient修饰的属性也没必要要求必须是可序列化的。
 *
 * @author shkstart
 * @create 2019 上午 10:38
 */
public class Person implements Serializable{


    private static final long serialVersionUID = 2822857307413847504L;

    private String name;
    private int age;
    private int id;
    /*
    如果某个类的属性不是基本数据类型或 String 类型，而是另一个
    引用类型，那么这个引用类型必须是可序列化的，否则拥有该类型的
    Field 的类也不能序列化。
    基本数据类型中的两种包装类Boolean和Character都实现了Serializable接口。
    剩下6种基本数据类型的包装类都继承了Number类，Number类实现了Serializable接口。
    String类也实现了Serializable接口。
     */
    private Account acct;

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person(String name, int age, int id, Account acct) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.acct = acct;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", acct=" + acct +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public Person() {

    }
}

class Account implements Serializable{

    private static final long serialVersionUID = 1071038247302976602L;

    private double balance;

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(double balance) {

        this.balance = balance;
    }
}
