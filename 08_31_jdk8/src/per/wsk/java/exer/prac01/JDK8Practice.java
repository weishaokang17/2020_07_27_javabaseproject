package per.wsk.java.exer.prac01;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author weishaokang
 * @date 2020-09-01 16:59
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class JDK8Practice {
    /*
    . 调用 Collections.sort()方法，通过定制排序比较两个 Employee(先按
    年龄比，年龄相同按姓名比),使用 Lambda 表达式作为参数传递。
     */
    @Test
    public void test01(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("美国队长",17));
        list.add(new Employee("蜘蛛侠",14));
        list.add(new Employee("钢铁侠",18));
        list.add(new Employee("绿巨人",15));

        System.out.println(list);

        Collections.sort(list,(o1,o2) -> {
            if (o1 == null || o2 == null) {
                throw new RuntimeException("对象不能为空");
            }

            if (o1.getAge() < o2.getAge()) {
                return -1;
            } else if (o1.getAge() > o2.getAge()) {
                return 1;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println(list);
    }


    /*
    ①声明函数式接口,接口中声明抽象方法：public String
    getValue(String str);
    ②声明类 LambdaTest，类中编写方法使用接口作为参数，将一个
    字符串转换成大写，并作为方法的返回值。
    ③再将一个字符串的第 2 个到第 4 个索引位置进行截取子串。
     */
    @Test
    public void test02(){
       StringInterface inter1 = str -> str.toUpperCase();

       String value = inter1.getValue("ewidasvc");

       System.out.println(value);

       StringInterface inter2 = str2 -> str2.substring(1,4);
       String value2 = inter2.getValue("iabcdefg");

       System.out.println(value2);

       //大写
        String dealString = dealString("abcdxyz", arg -> arg.toUpperCase());
        //截断
        String dealString1 = dealString("abcdxyz", arg1 -> arg1.substring(1, 4));

        System.out.println(dealString);
        System.out.println(dealString1);

        String s = dealString("abcdxyz", arg -> {
            if (arg == null) {
                throw new RuntimeException("参数不能为空");
            }
            return arg.toUpperCase();
        });

        System.out.println("s = " + s);

        String s1 = dealString("abcdxyz", arg -> {
            if (arg == null || arg.length() < 4) {
                throw new RuntimeException("字符串长度不够");
            }
            return arg.substring(1, 4);
        });

        System.out.println("s1 = " + s1);

    }


    public static String dealString(String str,StringInterface inter){
        return inter.getValue(str);
    }



    /*
    ①声明一个带两个泛型的函数式接口，泛型类型为<T,R> : T 为参
    数，R 为返回值。
    ②接口中声明对应抽象方法
    ③在 LambdaTest 类中声明方法，使用接口作为参数，计算两个 long
    型参数的和。
    ④再计算两个 long 型参数的乘积
     */
    @Test
    public void test03(){
        double count1 = getCount(123677L, 74975L, (arg0, arg1) -> Double.valueOf(arg1 + arg0));

        System.out.println(count1);

        double count2 = getCount(456, 324, (arg0, arg1) -> Double.valueOf(arg0 * arg1));

        System.out.println(count2);

    }

    public static double getCount(long arg1,long arg2,MyInterface<Long,Double> inter){
//        return inter.count(new Long(arg1),new Long(arg2)).doubleValue();
        return inter.count(arg1,arg2);
    }

}
