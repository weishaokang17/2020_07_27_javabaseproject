package per.wsk.java.study03;

import org.junit.Test;
import per.wsk.java.study01.Employee;
import per.wsk.java.study01.EmployeeData;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author weishaokang
 * @date 2020-09-02 17:17
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class StreamAPIOwnTest3 {

    static List<Employee> list = EmployeeData.getEmployees();

    @Test
    public void test01(){
        Predicate<Employee> pre = e -> e.getName().length() > 2;

        boolean b = list.stream().allMatch(pre);
        System.out.println(b);

        boolean b1 = list.stream().anyMatch(pre);
        System.out.println(b1);

        boolean b2 = list.stream().noneMatch(e -> e.getName().startsWith("马"));
        System.out.println(b2);


        Optional<Employee> first = list.stream().findFirst();
        System.out.println(first);

        System.out.println(list.stream().findAny());

        long count = list.stream().count();
        long count1 = list.stream().filter(pre).count();
        System.out.println(count);
        System.out.println(count1);

        System.out.println("****************************");

        Optional<Employee> max = list.stream().max((o1, o2) -> {
            if (o1.getSalary() > o2.getSalary()) {
                return 1;
            } else if (o1.getSalary() < o2.getSalary()) {
                return -1;
            } else {
                return 0;
            }
        });

        System.out.println(max);

        //
        Optional<Double> min = list.stream()
                .map(e -> e.getSalary())
//                .min((arg1, arg2) -> Double.compare(arg1, arg2));
                .min(Double::compareTo);

        System.out.println(min);
    }



    @Test
    public void test02(){
        List<Employee> collect = list.stream().collect(Collectors.toList());

        System.out.println(collect);

        List<Employee> collect1 = list.stream()
                .filter(e -> e.getSalary() > 4500)
                .collect(Collectors.toList());

        System.out.println(collect1);

        Set<Employee> set1 = list.stream()
                .filter(e -> e.getName().startsWith("马"))
                .collect(Collectors.toSet());

        System.out.println(set1);


    }




}
