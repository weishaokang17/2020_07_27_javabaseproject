package per.wsk.java.study02date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * jdk 8之前的日期时间的API测试
 * 1. System类中currentTimeMillis();
 * 2. java.util.Date和子类java.sql.Date
 * 3. SimpleDateFormat
 * 4. Calendar
 *
 * @author shkstart
 * @create 2019 上午 11:35
 */
public class DateTimeTest {
    /*
    SimpleDateFormat的使用：SimpleDateFormat对日期Date类的格式化和解析

    1.两个操作：
    1.1 格式化：日期 --->字符串
    1.2 解析：格式化的逆过程，字符串 ---> 日期

    2.SimpleDateFormat的实例化

     */
    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat:使用默认的构造器   空参构造器默认的日期格式2020/08/02 上午11:43
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期 --->字符串
        Date date = new Date();
        System.out.println(date);

        String format = sdf.format(date);
        System.out.println(format);

        //解析：格式化的逆过程，字符串 ---> 日期
        String str = "2020/08/02 上午11:43";
        Date date1 = sdf.parse(str);//sdf空参构造器默认的格式：2020/08/02 上午11:43
        System.out.println(date1);

        //*************按照指定的方式格式化和解析：调用带参的构造器*****************
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//小写m表示分钟，大写M表示月份
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//h小写是12小时制，大写H是24小时制
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);//2020-08-02 17:47:29
        //解析:要求字符串必须是符合SimpleDateFormat识别的格式(通过构造器参数体现),
        //否则，抛异常
        Date date2 = sdf1.parse("2020-08-02 17:47:29");
        System.out.println(date2);
    }

    /*
    练习一：字符串"2020-09-08"转换为java.sql.Date

     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(birth);
//        System.out.println(date);

        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);
    }

    /**
     * 练习二："三天打渔两天晒网"   1990-01-01  xxxx-xx-xx 打渔？晒网？
     *
     *     举例：2020-09-08 ？ 总天数
     *
     *
     *
     *     总天数的计算？
     *     方式一：( date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24) + 1
     *     方式二：1990-01-01  --> 2019-12-31  +  2020-01-01 -->2020-09-08
     *
     *             总天数 % 5 == 1,2,3 : 打渔
     *             总天数 % 5 == 4,0 :   晒网
     *
     *     显然，方式一更简单
     *
     */
    public static void main(String[] args) throws ParseException {
        //注意：通过Scanner测试，不能用junit，故这里用main方法
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入日期：(格式YYYY-MM-dd)");
        String dateStr = sc.next();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("输入日期不符合格式要求");
            return;
        }

        String startTimeStr = "1990-01-01";
        Date startDate = sdf.parse(startTimeStr);

        double day = (date.getTime() - startDate.getTime()) /(double) (1000*60*60*24);
        int day1 = (int)day;
        if (day - day1 > 0) {
            day1++;
        }

        int i = day1 % 5;
        if (i == 0 || i == 4 ) {
            System.out.println("渔夫在" + dateStr + "这天在晒网");
        } else {
            System.out.println("渔夫在" + dateStr + "这天在打鱼");
        }
    }


    /*
    Calendar日历类(抽象类）的使用

     */
    @Test
    public void testCalendar(){
        //1.实例化
        //方式一：创建其子类（GregorianCalendar）的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());

        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);//calendar对象中的日期是月的第几天
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //calendar对象中的日期是这一年的第几天

        //set()
        //calendar可变性
        calendar.set(Calendar.DAY_OF_MONTH,22);//把calendar对象中的日期设置成这个月的第22天
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //add()
        calendar.add(Calendar.DAY_OF_MONTH,-3);
        //把calendar对象中的日期的月份设置成这个月减掉3个月（如原来是8月，设置后改成了5月）
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //getTime():日历类---> Date  //日历类转为Date类
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime():Date ---> 日历类  //Date类转为日历类
        Date date1 = new Date(759636467462L);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        days = calendar1.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        /*
        注意: 1. 获取月份时：一月是0，二月是1，以此类推，12月是11
              2. 获取星期时：周日是1，周二是2 ， 。。。。周六是7
         */
    }
}
