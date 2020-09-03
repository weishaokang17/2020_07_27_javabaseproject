package per.wsk.exer.prac01;

import org.junit.Test;
import per.wsk.exer.prac02.Trader;
import per.wsk.exer.prac02.Transaction;
import per.wsk.java.study01.Employee;
import per.wsk.java.study01.EmployeeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author weishaokang
 * @date 2020-09-02 22:59
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class StreamAPIPractice {

    /*
    给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
    例如，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
     */
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        List<Double> collect = list.stream().map(i -> Math.pow(i, 2)).collect(Collectors.toList());
        List<Integer> collect = list.stream().map(i -> i * i).collect(Collectors.toList());

        System.out.println(collect);
    }

    /*
    怎样用 map 和 reduce 方法数一数流中有多少个 Employee 呢？
     */
    @Test
    public void test02(){
        List<Employee> list = EmployeeData.getEmployees();

        Optional<Integer> reduce = list.stream().map(e -> 1).reduce(Integer::sum);

        Integer integer = reduce.get();

        System.out.println("Employees中的Employee个数是： "+integer);

    }


    /*
    题目：
    1. 找出 2011 年发生的所有交易， 并按交易额排序（从低到高）
    2. 交易员都在哪些不同的城市工作过
    3. 查找所有来自剑桥的交易员，并按姓名排序
    4. 返回所有交易员的姓名字符串，按字母顺序排序
    5. 有没有交易员是在米兰工作的
    6. 打印生活在剑桥的交易员的所有交易额
    7. 所有交易中，最高的交易额是多少
    8. 找到交易额最小的交易
     */
    public static Trader trader1 = new Trader("凯尔特人","波士顿");
    public static Trader trader2 = new Trader("火箭","休斯顿");
    public static Trader trader3 = new Trader("湖人","洛杉矶");
    public static Trader trader4 = new Trader("快船","洛杉矶");
    public static Trader trader5 = new Trader("老鹰","亚特兰大");
    public static Trader trader6 = new Trader("公牛","芝加哥");
    public static Trader trader7 = new Trader("步行者","印第安纳");

    public static List<Trader> list1 = new ArrayList<>();
    static {
        list1.add(trader1);
        list1.add(trader2);
        list1.add(trader3);
        list1.add(trader4);
        list1.add(trader5);
        list1.add(trader6);
        list1.add(trader7);
    }

    public static Transaction transstion1 = new Transaction(trader1,2009,1000000);
    public static Transaction transstion2 = new Transaction(trader1,1909,1400);
    public static Transaction transstion3 = new Transaction(trader1,2010,17000);
    public static Transaction transstion4 = new Transaction(trader2,2011,10400);
    public static Transaction transstion5 = new Transaction(trader4,2011,17000);
    public static Transaction transstion6 = new Transaction(trader7,2014,12000);

    public static List<Transaction> list = new ArrayList<>();
    static {
        list.add(transstion1);
        list.add(transstion2);
        list.add(transstion3);
        list.add(transstion4);
        list.add(transstion5);
        list.add(transstion6);
    }

    /*
    1. 找出 2011 年发生的所有交易， 并按交易额排序（从低到高）
    2. 交易员都在哪些不同的城市工作过
    3. 查找所有来自剑桥的交易员，并按姓名排序
    4. 返回所有交易员的姓名字符串，按字母顺序排序
    5. 有没有交易员是在米兰工作的
    6. 打印生活在剑桥的交易员的所有交易额
    7. 所有交易中，最高的交易额是多少
    8. 找到交易额最小的交易
     */
    @Test
    public void test03(){
        //1.找出 2011 年发生的所有交易， 并按交易额排序（从低到高）
        list.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted((t1,t2) ->{
                   if (t1.getValue()>t2.getValue()) {
                       return 1;
                   } else if (t1.getValue()<t2.getValue()) {
                       return -1;
                   } else {
                       return 0;
                   }
                })
                .forEach(System.out::println);

        System.out.println("*********************************");
        //2.交易员都在哪些不同的城市工作过
        list1.stream().map(t -> t.getCity()).distinct().forEach(System.out::println);

        System.out.println("*********************************");
        //3.查找所有来自剑桥的交易员，并按姓名排序   此处把剑桥改成洛杉矶了
        list1.stream()
                .filter(e -> "洛杉矶".equals(e.getCity()))
                .sorted((o1,o2) ->{
                    return o1.getName().compareTo(o2.getName());
                })
                .forEach(System.out::println);

        System.out.println("*********************************");
        //4. 返回所有交易员的姓名字符串，按字母顺序排序
        List<String> collect = list1.stream().map(t -> t.getName()).sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(collect);

        //5. 有没有交易员是在米兰工作的
        System.out.println("*********************************");
        boolean b = list1.stream().anyMatch(t -> "波士顿".equals(t.getCity()));
        System.out.println(b);


        //6. 打印生活在剑桥的交易员的所有交易额
        System.out.println("*********************************");
        Optional<Integer> reduce = list.stream().filter(t -> "洛杉矶".equals(t.getTrader().getCity()))
                .map(t -> t.getValue()).reduce(Integer::sum);

        Integer integer = reduce.get();
        System.out.println(integer);

        //7. 所有交易中，最高的交易额是多少
        System.out.println("*********************************");
        Optional<Integer> integer1 = list.stream().max((t1, t2) -> {
            if (t1.getValue() > t2.getValue()) {
                return 1;
            } else if (t1.getValue() < t2.getValue()) {
                return -1;
            } else {
                return 0;
            }
        }).map(t -> t.getValue());

        Integer integer2 = integer1.get();

        System.out.println(integer2);

        //8. 找到交易额最小的交易
        System.out.println("*********************************");
        Optional<Transaction> min = list.stream().min((t1, t2) -> {
            if (t1.getValue() > t2.getValue()) {
                return 1;
            } else if (t1.getValue() < t2.getValue()) {
                return -1;
            } else {
                return 0;
            }
        });

        Transaction transaction = min.get();

        System.out.println(transaction);

    }

}
