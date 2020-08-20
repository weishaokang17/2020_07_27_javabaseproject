package per.wsk.exer.prac01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author weishaokang
 * @date 2020-08-04 17:43
 * @project 2020_07_27_javabaseproject
 * @description:
 */
@Target(ElementType.METHOD)
public @interface MyTiger {

    String value() default " ";
    int[] arr();

}
