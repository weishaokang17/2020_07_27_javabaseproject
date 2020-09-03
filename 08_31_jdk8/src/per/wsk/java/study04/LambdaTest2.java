package per.wsk.java.study04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 *
 * 消费型接口 Consumer<T>     void accept(T t)
 * 供给型接口 Supplier<T>     T get()
 * 函数型接口 Function<T,R>   R apply(T t)
 * 断定型接口 Predicate<T>    boolean test(T t)
 *
 *
 * @author shkstart
 * @create 2019 下午 2:29
 */
public class LambdaTest2 {

    public static final Consumer<Date>  con = (arg) -> {
        System.out.println("***********************");
        System.out.println("***********************");
    };

    @Test
    public void test1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了，去天上人间买了瓶矿泉水，价格为：" + aDouble);
            }
        });

        System.out.println("********************");

        happyTime(400,money -> System.out.println("学习太累了，去天上人间喝了口水，价格为：" + money));
    }

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }


    @Test
    public void test2(){
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");

        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });

        System.out.println(filterStrs);


        List<String> filterStrs1 = filterString(list,(String s) -> {
            System.out.println("判断当前字符串  " + s + "  是否包含 '京' 字");
            return s.contains("京");
        });
        System.out.println(filterStrs1);
    }

    //根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre){

        ArrayList<String> filterList = new ArrayList<>();

        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }

        return filterList;

    }


    @Test
    public void test03(){
        String[] arr = {"abc","fuckyou","bed","weisb","zh","zs","back"};
        List<String> list = Arrays.asList(arr);

        List<String> resultList = method(str -> ((String)str).contains("b")
        , list);

        System.out.println(resultList);
    }

    public static List<String> method(Predicate pre,List<String> list){
        if (list == null || list.size() == 0) {
            return null;
        }
        List<String> resultList = new ArrayList<>();

        for(String str : list){
            if (pre.test(str)) {
                resultList.add(str);
            }
        }
        return resultList;
    }


    @Test
    public void test04(){
        String[] arr = {"abcrteyxue","fuckyou","bezs32oid435","weisb87","zh>=-=",
                "zs23421hf", "back78945662"};

        List<String> list = Arrays.asList(arr);

        Function<String,Integer> func = str -> str == null?0:str.length();

        int i = method2(list, func);

        int j = method2(list, str -> str == null ? 0 : ((String) str).length());


        System.out.println(i);
        System.out.println(j);
    }

    public static int method2(List<String> list, Function func){
        int count=0;
        if (list == null || list.size() < 0) {
            return 0;
        }
        for(String str : list){
            count = (Integer) func.apply(str) > 7 ? count+1:count;
        }

        return count;
    }

}
