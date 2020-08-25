package per.wsk.exer.prac02;

import org.junit.Test;

/**
 * @Author weishaokang
 * @date 2020-08-24 21:51
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class AppleTest {

    @Test
    public void test01(){
        Apple<String> apple1 = new Apple<>("500g");
        Apple<Integer> apple2 = new Apple<>(500);
        Apple<Double> apple3 = new Apple<>(500.0);

        System.out.println(apple1);
        System.out.println(apple2);
        System.out.println(apple3);
    }

}
