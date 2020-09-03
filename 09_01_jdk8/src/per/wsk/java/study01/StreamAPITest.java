package per.wsk.java.study01;

import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. Stream关注的是对数据的运算，与CPU打交道
 *    集合关注的是数据的存储，与内存打交道
 *
 *   可以把Stream看成是一个流水线。
 *
 * 2.
 * ①Stream 自己不会存储元素。
 * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 *
 * 3.Stream 执行流程
 * ① Stream的实例化
 * ② 一系列的中间操作（过滤、映射、...)
 * ③ 终止操作
 *
 * 4.说明：
 * 4.1 一个中间操作链，对数据源的数据进行处理
 * 4.2 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *
 *
 *  测试Stream的实例化
 *
 * @author shkstart
 * @create 2019 下午 4:25
 */
public class StreamAPITest {

    //创建 Stream方式一：通过集合
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

//        default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();

//        default Stream<E> parallelStream() : 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();

        /*
        顺序流也叫串行流，串行流和并行流类似电路中的串联和并联。
         */

        List<Object> list = new ArrayList<>();
        Stream<Object> stream1 = list.stream();
        Stream<Object> objectStream = list.parallelStream();

        List list1 = new ArrayList<>();
        Stream stream2 = list1.stream();
        /*
        集合框架中只有Collection框架可以创建Stream流，Map框架
        没有创建Stream流的方法，意味着HashMap等集合是无法创建Stream流的。
         */
    }



    //创建 Stream方式二：通过数组
    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        //调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Jerry");
        Employee[] arr1 = new Employee[]{e1,e2};

        Stream<Employee> stream1 = Arrays.stream(arr1);

    }



    //创建 Stream方式三：通过Stream的of()
    @Test
    public void test3(){

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Object> stream1 = Stream.of("abc", new Date(), 147, new HashMap(),
                new ArrayList(), new Employee());

        Stream<? extends Serializable> stream2 = Stream.of("aed", 12, LocalDateTime.now());

    }



    //创建 Stream方式四：创建无限流
    @Test
    public void test4(){

//      迭代   第一个参数是种子，意思是这个无限流从哪个位置开始（开始的这个数据就是种子）
//      public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);


//      生成
//      public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);
        Stream.generate(()->{
            return Math.random();
        }).limit(10).forEach(System.out::println);

        Stream.generate(new Supplier<Double>(){
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(10).forEach(System.out::println);
    }

}
