package per.wsk.parc.exer03;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author weishaokang
 * @date 2020-08-03 20:06
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class CommonClassTest {

    /*
    I am a student!   写一个方法：实现输出 !student a am I
     */
    @Test
    public void test01(){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("输入一个字符串");
//        String str = sc.next();
        String str = "I am your father!";//因为junit不能测试Scanner
        String result = reverseString(str);
        System.out.println(result);
    }

    public static String reverseString(String str){
        String[] arr = str.split(" ");
        StringBuffer sf = new StringBuffer();
        for (int i = arr.length-1; i >= 0; i--) {
            String temp = arr[i];
            sf.append(temp);
            if (i != 0) {
                sf.append(" ");
            }
        }
        return sf.toString();
    }


    @Test
    public void test02(){
        String str = "ertifdsADdwDFDqdf 345 WYx";
        int[] sum = getSum(str);
        System.out.println("大写字母个数是：" + sum[0] +
                " 个，小写字母个数是: " + sum[1] + " 个");
    }

    /*
    任意给定的一串字母，统计字符串里面的大写字母和小写字母的个数。
    a-z：97-122
    A-Z：65-90
    0-9：48-57
     */
    public static int[] getSum(String str){
        int[] arr = new int[2];
        if (str != null && str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                    arr[0]++;
                } else if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                    arr[1]++;
                }
            }
        }
        return arr;
    }

    /*
    1.根据传入得路径，获取文件名。例如：D:\myfile\hello.java取出hello.java
    2.根据传入得路径，获取文件的类型名。例如：D:\myfile\hello.java取出.java
     */
    @Test
    public void test03(){
        String str = "D:\\myfile\\hello.java";
        String fileName = method01(str);
        System.out.println(fileName);

        System.out.println(method02(str));
    }

    public static String method01(String str){
        int i = str.lastIndexOf("\\");
        String fileName = str.substring(i + 1, str.length());
        return fileName;
    }

    public static String method02(String str){
        int i = str.lastIndexOf(".");
        String name = str.substring(i + 1, str.length());
        return name;
    }

    /*
    求两个日期之间相隔的天数
     */
    @Test
    public void test04(){
        LocalDateTime localDateTime1 = LocalDateTime.of(2019,12,21,17,24,27);
        Date date1 = Date.from( localDateTime1.atZone( ZoneId.systemDefault()).toInstant());

        long time1 = date1.getTime();

        LocalDateTime localDateTime2 = LocalDateTime.of(2007,2,7,7,14,9);
        Date date2 = Date.from( localDateTime2.atZone( ZoneId.systemDefault()).toInstant());

        long time2 = date2.getTime();

        long day = (time1 - time2) / (24 * 60*60*1000);
        if ((time1 - time2)%(24 * 60*60*1000)!=0) {
            day++;
        }
        System.out.println("相差天数是: "+day);
    }


    /*
    编写java程序，随便输入两个单词，两个单词之间以空格隔开，
    输出时每个单词的首字母变为大写。
    如输入：“hello java”，输出为“Hello Java”
    a-z：97-122
    A-Z：65-90
    0-9：48-57
     */
    @Test
    public void test05(){
        String str = "trei erwi truigdj trei";


        String[] s = str.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length; i++) {

            char[] charArr = new char[s[i].length()];

            for (int j = 0; j < s[i].length(); j++) {
                if (j == 0) {
                    char charFirst = s[i].charAt(0);
                    if (charFirst<=122 && charFirst>=97) {
                        charArr[0] = (char)(charFirst - 32);
                    } else {
                        charArr[0] = charFirst;
                    }
                } else {
                    charArr[j] = s[i].charAt(j);
                }
            }
            sb.append(charArr);
            if (i != s.length-1) {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());

    }


    /*
    求出“hijavahehejavahejava”字符串里面包含几个“java字符串。”
     */
    @Test
    public void test06(){
        String longStr = "aaaaertitertiooaa";
        String shortStr = "aa";
        int count = 0;
        for (int i = 0; i <= longStr.length()-shortStr.length(); i++) {
            if (longStr.substring(i,i+shortStr.length()).equals(shortStr)) {
                count++;
            }
        }
        System.out.println(count);
    }


    /*
    输出字符串"ddejidsEFALDFfnef2357 3ed"里的大写字母数，小写英文字母数，非英文字母数
    a-z：97-122
    A-Z：65-90
    0-9：48-57
     */
    @Test
    public void test07(){
        String str = " ddejidsEFA LDFfnef2357 3ed";
        int count1,count2,count3;
        count1 = count2 = count3 = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 97 && c <= 122) {
                count1++;
            } else if (c >= 65 && c <= 90) {
                count2++;
            } else if (c>=48 && c <= 57) {
                count3++;
            }
        }

        System.out.println("小写字母个数 " + count1 + "" +
                " ,大写字母个数 " + count2 + " ,数字个数 " + count3);
    }


    /*
    输入一句5个字的话，然后将它逆序输出。例如：
     */
    @Test
    public void test08(){
        String str = "波士顿凯尔特人总冠军";
        StringBuilder reverse = new StringBuilder(str).reverse();
        System.out.println(reverse.toString());
    }



}
