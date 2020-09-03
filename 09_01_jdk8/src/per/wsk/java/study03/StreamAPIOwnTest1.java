package per.wsk.java.study03;

import org.junit.Test;
import per.wsk.java.study01.Employee;
import per.wsk.java.study01.EmployeeData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author weishaokang
 * @date 2020-09-01 21:08
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 流操作的自己的练习
 */
public class StreamAPIOwnTest1 {

    static List<Employee> list = EmployeeData.getEmployees();

    @Test
    public void test01(){

        Stream<Employee> stream = list.stream();

        System.out.println(stream);//输出的是地址   Stream没有重写toString()方法。

        Stream<Employee> stream1 = stream.filter(e -> e.getSalary() > 8000);

        stream1.forEach(System.out::println);

        //注意：stream相当于是一个流水线，上面的stream1的过滤操作其实并没有执行，
        // 代码运行到下面的forEach()方法时，forEach()方法属于一种终止操作，
        // 此时stream1才进行上面的过滤操作。

        //另外，一个stream流终止后，就不能再次使用了。下面注释的两行代码会报异常
        /*Consumer<Employee> con = System.out::println;
        stream1.forEach(con);*/

        System.out.println("*******************");

        //下面是连起来写的，但下面注释掉的这行会报异常，因为stream已经使用过了。
        /*stream.filter(e -> e.getSalary() > 7000).
                forEach(System.out::println);*/

        list.stream().filter(e -> e.getSalary()>6000).forEach(System.out::println);
    }

    @Test
    public void test02(){
        list.stream().limit(4).forEach(System.out::println);

        System.out.println("************************");

        list.stream().skip(2).forEach(System.out::println);

        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",41,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));

        System.out.println("*******************************");

        for(Employee e:list){
            System.out.println(e);
        }

        System.out.println("****************************");

        list.stream().distinct().forEach(System.out::println);

    }


    @Test
    public void test03(){
//        sorted()——自然排序
        List<Integer> listNum = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        listNum.stream().sorted().forEach(System.out::println);

        list.stream().sorted().forEach(System.out::println);

        System.out.println("***********************");

//        sorted(Comparator com)——定制排序
        Comparator<Employee> com = new Comparator<>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getAge()>o2.getAge()) {
                    return 1;
                } else if (o1.getAge()<o2.getAge()) {
                    return -1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        };

        list.stream().sorted(com).forEach(System.out::println);

        System.out.println("***********************");

        list.stream().sorted((o1,o2) -> {
            if (o1.getAge()>o2.getAge()) {
                return 1;
            } else if (o1.getAge()<o2.getAge()) {
                return -1;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }).forEach(System.out::println);

    }



}
