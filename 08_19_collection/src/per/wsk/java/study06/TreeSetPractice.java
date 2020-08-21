package per.wsk.java.study06;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author weishaokang
 * @date 2020-08-21 12:04
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * 1. 定义一个 Employee 类。
 * 该类包含：private 成员变量 name,age,birthday，其中 birthday 为
 * MyDate 类的对象；
 * 并为每一个属性定义 getter, setter 方法；
 * 并重写 toString 方法输出 name, age, birthday
 *
 * MyDate 类包含:
 * private 成员变量 year,month,day；并为每一个属性定义 getter, setter
 * 方法；
 *
 * 创建Employee类的 5 个对象，并把这些对象放入 TreeSet 集合中（下一章：
 * TreeSet 需使用泛型来定义）
 *
 * 分别按以下两种方式对集合中的元素进行排序，并遍历输出：
 * 1). 使 Employee 实现 Comparable 接口，并按 name 排序
 * 2). 创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序。
 */
public class TreeSetPractice {

    public static void main(String[] args) {

        Employee kevin = new Employee("kevin", 42, new MyDate(1978, 5, 5));
        Employee tom = new Employee("tom", 41, new MyDate(1979, 5, 5));
        Employee marry = new Employee("marry", 22, new MyDate(1998, 5, 5));
        Employee jack = new Employee("jack", 12, new MyDate(2008, 5, 5));
        Employee tatum = new Employee("tatum", 21, new MyDate(1999, 5, 5));

        TreeSet<Employee> treeSet = new TreeSet<>();
        treeSet.add(kevin);
        treeSet.add(tom);
        treeSet.add(marry);
        treeSet.add(jack);
        treeSet.add(tatum);


        treeSet.forEach(System.out::println);

        System.out.println("-----------------------------------------");

        for (Employee ele:treeSet) {
            System.out.println(ele);
        }

        /*
        上面使用lamda表达式和下面的增强for循环输出的结果都是：
        Employee{name='jack', age=12, birthday=MyDate{year=2008, month=5, day=5}}
        Employee{name='kevin', age=42, birthday=MyDate{year=1978, month=5, day=5}}
        Employee{name='marry', age=22, birthday=MyDate{year=1998, month=5, day=5}}
        Employee{name='tatum', age=21, birthday=MyDate{year=1999, month=5, day=5}}
        Employee{name='tom', age=41, birthday=MyDate{year=1979, month=5, day=5}}
         */
    }

    @Test
    public void test01(){
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee) {
                    int o1Year = ((Employee) o1).getBirthday().getYear();
                    int o2Year = ((Employee) o2).getBirthday().getYear();
                    int o1Month = ((Employee) o2).getBirthday().getMonth();
                    int o2Month = ((Employee) o2).getBirthday().getMonth();
                    int o1Day = ((Employee) o2).getBirthday().getDay();
                    int o2Day = ((Employee) o2).getBirthday().getDay();
                    if (o1Year!=o2Year) {
                        return o1Year>o2Year?-1:1;
                    } else if (o1Month!=o2Month) {
                        return o1Month>o2Month?-1:1;
                    } else if (o1Day!=o2Day) {
                        return o1Day>o2Day?-1:1;
                    }
                    return 0;
                } else {
                    throw new RuntimeException("参数有误");
                }
            }
        };

        Employee kevin = new Employee("kevin", 42, new MyDate(1978, 5, 5));
        Employee tom = new Employee("tom", 41, new MyDate(1979, 5, 5));
        Employee marry = new Employee("marry", 22, new MyDate(1998, 5, 5));
        Employee jack = new Employee("jack", 12, new MyDate(2008, 5, 5));
        Employee tatum = new Employee("tatum", 21, new MyDate(1999, 5, 5));

        TreeSet<Employee> treeSet = new TreeSet<>(com);
        treeSet.add(kevin);
        treeSet.add(tom);
        treeSet.add(marry);
        treeSet.add(jack);
        treeSet.add(tatum);

        treeSet.forEach(System.out::println);

        System.out.println("-----------------------------------------");

        for (Employee ele:treeSet) {
            System.out.println(ele);
        }

        /*
        Employee{name='jack', age=12, birthday=MyDate{year=2008, month=5, day=5}}
        Employee{name='tatum', age=21, birthday=MyDate{year=1999, month=5, day=5}}
        Employee{name='marry', age=22, birthday=MyDate{year=1998, month=5, day=5}}
        Employee{name='tom', age=41, birthday=MyDate{year=1979, month=5, day=5}}
        Employee{name='kevin', age=42, birthday=MyDate{year=1978, month=5, day=5}}
        -----------------------------------------
        Employee{name='jack', age=12, birthday=MyDate{year=2008, month=5, day=5}}
        Employee{name='tatum', age=21, birthday=MyDate{year=1999, month=5, day=5}}
        Employee{name='marry', age=22, birthday=MyDate{year=1998, month=5, day=5}}
        Employee{name='tom', age=41, birthday=MyDate{year=1979, month=5, day=5}}
        Employee{name='kevin', age=42, birthday=MyDate{year=1978, month=5, day=5}}
         */

    }



}
