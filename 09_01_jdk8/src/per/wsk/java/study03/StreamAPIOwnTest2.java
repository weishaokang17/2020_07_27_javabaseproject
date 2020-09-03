package per.wsk.java.study03;

import org.junit.Test;
import per.wsk.java.study01.Employee;
import per.wsk.java.study01.EmployeeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Author weishaokang
 * @date 2020-09-02 12:04StreamAPITest2
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class StreamAPIOwnTest2 {

    static List<Employee> list = EmployeeData.getEmployees();

    @Test
    public void test01(){
       list.stream().map(new Function<Employee, Object>() {
           @Override
           public Object apply(Employee employee) {
               return employee.getName();
           }
       }).forEach(System.out::println);

        System.out.println("*****************************");

        List list1 = Arrays.asList("aa", "bb", "cc", "dd");

       list1.stream().map(new Function<String,Integer>() {
           @Override
           public Integer apply(String o) {
               return null;
           }
       }).forEach(System.out::println);

        System.out.println("*********************************");

        List list2 = Arrays.asList("281", "150", "129", "114");

       /*Function<String,Integer> fun = new Function<>(){
           @Override
           public Integer apply(String s) {
               return Integer.parseInt(s);
           }
       };*/
        Function<String,Integer> fun = s -> Integer.parseInt(s);

        Stream<Integer> stream = list2.stream().map(fun);
        stream.forEach(System.out::println);

    }

    @Test
    public void test02(){
        //练习1：获取员工姓名长度大于3的员工的姓名。
        Stream<String> stringStream = list.stream().map(employee -> employee.getName());
        Stream<String> stringStream1 = stringStream.filter(str -> str.length() > 3);
        stringStream1.forEach(System.out::println);

        System.out.println("****************************");

        //把上面的写法连在一起
        list.stream().map(employee -> employee.getName())
                .filter(str -> str.length()>3)
                .forEach(System.out::println);

        System.out.println("*******************************");

        //也可以先过滤名字的长度，再把名字映射到另一个流中
        list.stream().filter(employee -> employee.getName().length()>3)
                .map(employee -> employee.getName())
                .forEach(System.out::println);
    }


    @Test
    public void test03(){
        List list1 = Arrays.asList("aa", "bb", "cc", "dd");

        Function<String,Stream<Character>> fun = new Function<>() {
            @Override
            public Stream apply(String str) {
                char[] arr = str.toCharArray();
                List<Character> list2 = new ArrayList<>(arr.length);
                for(char c:arr){
                   list2.add(c);
                }
                return list2.stream();
            }
        };

        list1.stream().flatMap(fun).forEach(System.out::println);

        System.out.println("*****************************");

        list1.stream().flatMap(str -> {
            char[] arr = ((String)str).toCharArray();
            List<Character> list2 = new ArrayList<>(arr.length);
            for(char c:arr){
                list2.add(c);
            }
            return list2.stream();
        }).forEach(System.out::println);
    }

}
