package per.wsk.java.study02;

import org.junit.Test;

/**
 * 一道面试题
 * @author shkstart
 * @create 2019 上午 11:32
 */
public class StringTest {

    String str = "good";
    char[] ch = { 't', 'e', 's', 't' };
    final char[] ch2 = {'a','b','c','d'};

    public void change(String str, char[] ch) {
        str = "test ok";
        ch[0] = 'b';
    }
    public static void main(String[] args) {
        StringTest ex = new StringTest();

        String s = new String(ex.ch);
        System.out.println("s: "+s);//test
        ex.change(ex.str, ex.ch);
        System.out.println("s: "+s);//test
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best

        ex.ch = new char[]{'a'};
//        ex.ch2 = new char[]{'a'};
    }

    @Test
    public void test01(){
        String str = "abcd";
        final char[] charArray = new char[]{'a','b','c','d'};
        System.out.println(str);//abcd
        System.out.println(charArray);//abcd
        method1(str,charArray);

        System.out.println(str);//abcd
        System.out.println(charArray);//abce
    }

    public void method1(String str,char[] arr){
        str = "abce";
        arr[3]='e';
        System.out.println(str);//abce
    }


    @Test
    public void test02(){
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");
        s1 += "world";
        String s4 = "hello" + "world";
        final String s5 = s1 + "world";

        final String s6 = "hello";
        final String s7 = "world";

        System.out.println(s4==s5);
        String s8 = s6 + s7;
        System.out.println(s8 == s4);
    }
}

