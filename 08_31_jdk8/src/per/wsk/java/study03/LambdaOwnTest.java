package per.wsk.java.study03;

import org.junit.Test;

import java.util.Date;

import static java.lang.System.*;

/**
 * @Author weishaokang
 * @date 2020-09-01 11:23
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class LambdaOwnTest {

    @Test
    public void test01(){
        UserDAO dao = new UserDAO(){
            @Override
            public void excuteData(String str) {
                if (str != null && str.length() > 0) {
                    out.println(str);
                }
            }
        };

        UserDAO dao2 = (str) ->{
            if (str != null && str.length() > 0) {
                out.println(str);
            }
        };

        UserDAO dao3 = str -> {
            if (str != null && str.length() > 0) {
                out.println(str);
            }
        };

        UserDAO dao4 = str -> {
            out.println("**************");
            out.println(str);
        };

        UserDAO dao5 = str -> out.println(str);

    }


    @Test
    public void test02(){

//        AccountDAO dao = (str,date) -> out.println("-----------");

//        AccountDAO dao2 = (str,date) -> str+"abc";

        AccountDAO<Integer> dao3 = (stringArg,dateArg) -> {
            return stringArg.toString();
        };

        String s = dao3.excuteData(new Integer(2), new Date());


        //下面这行代码，接口的实现类dao4，没有指明泛型，那就认为stringArg这个参数是Object类型。

        AccountDAO dao4 = (stringArg,dateArg) -> {
            out.println(stringArg.getClass());
            return stringArg.toString();
        };

        dao4.excuteData(new Date(),new Date());
    }


}
