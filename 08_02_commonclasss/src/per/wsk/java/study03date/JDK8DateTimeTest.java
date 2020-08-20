package per.wsk.java.study03date;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * jdk 8中日期时间API的测试
 *
 * jdk1.8以前关于日期类的功能很不全面，
 * 对日期和时间的操作一直是Java程序员最痛苦的地方之一，
 * jdk1.8对日期类新增了很多功能。
 *
 * @author shkstart
 * @create 2019 下午 2:44
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate(){
        //偏移量
        //Date中的年份是从1900开始的，而月份都从0开始。
        Date date = new Date(2020,9,8);
        Date date1 = new Date(2020 - 1900,9 - 1,8);
        System.out.println(date);// Fri Oct 08 00:00:00 CST 3920
        System.out.println(date1);//Tue Sep 08 00:00:00 GMT+08:00 2020
    }

    /*
    LocalDate、LocalTime、LocalDateTime 的使用
    说明：  LocalDate是日期、LocalTime是时间、LocalDateTime是 日期+时间
        1.LocalDateTime相较于LocalDate、LocalTime，使用频率要高
        2.类似于Calendar
        3.三个类基本类似，获取实例、获取相关的属性、设置相关的属性等方法基本都一样。
     */
    @Test
    public void test1(){
        //now():获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);//2020-08-02  日期
        System.out.println(localTime);//20:37:09.326124 时间
        System.out.println(localDateTime);//2020-08-02T20:37:09.326124 日期+时间

        //of():设置指定的年、月、日、时、分、秒。没有偏移量
        // of()方法重载的有好多，下面这列只是其中一个
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
        System.out.println(localDateTime1);//2020-10-06T13:23:43


        //getXxx()：获取相关的属性

        //获取localDateTime对象的日期是当前月的第几天
        System.out.println(localDateTime.getDayOfMonth());
        //获取localDateTime对象的日期是星期几
        System.out.println(localDateTime.getDayOfWeek());
        //获取localDateTime对象的月份
        System.out.println(localDateTime.getMonth());//AUGUST
        //获取localDateTime对象的月份(阿拉伯数字)
        System.out.println(localDateTime.getMonthValue());//8
        //获取localDateTime对象的分钟
        System.out.println(localDateTime.getMinute());

        //体现不可变性
        //withXxx():设置相关的属性
        //设置日期是当前月的第22天   注意:原来的localDate对象的日期不发生改变。
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);
        System.out.println(localDate1);

        //设置日期的小时是第4个小时    原来的localDateTime的小时不发生改变
        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);

        //不可变性
        //设置日期的月份比之前加3, 原来的localDateTime月份不改变
        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime);
        System.out.println(localDateTime3);

        //设置日期的天数比之前减6, 原来的localDateTime天数不变
        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime);
        System.out.println(localDateTime4);
    }

    /*
    Instant的使用
    类似于 java.util.Date类

    ①now() 静态方法，返回默认UTC时区的Instant类的对象
    ②ofEpochMilli(long epochMilli) 静态方法，返回在1970-01-01 00:00:00基础上加上指定毫秒
        数之后的Instant类的对象
    ③atOffset(ZoneOffset offset) 结合即时的偏移来创建一个 OffsetDateTime对象
    ④toEpochMilli() 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳
     */
    @Test
    public void test2(){
        //now():获取本初子午线对应的标准时间,比东八区的北京时间晚8个小时
        Instant instant = Instant.now();
        System.out.println(instant);//2020-08-02T13:05:16.269112900Z

        //ofEpochMilli():通过给定的毫秒数，获取Instant实例  -->类似于Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1550475314878L);
        System.out.println(instant1);

        //以上两个方法都是获取Instant对象的方法

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2020-08-02T21:05:16.269112900+08:00

        //toEpochMilli():获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数  ---> 类似于Date类的getTime()
        long milli = instant.toEpochMilli();
        System.out.println(milli);

    }

    /*
    DateTimeFormatter:格式化或解析日期、时间
    类似于SimpleDateFormat
        该类有三种格式化方法：
        方式一：预定义的标准格式。
        方式二：本地化相关的格式。
        方式三：自定义的格式。
     */
    @Test
    public void test3(){
//        方式一：预定义的标准格式。
//        如：ISO_LOCAL_DATE_TIME;  日期时间格式
//        ISO_LOCAL_DATE;           日期格式
//        ISO_LOCAL_TIME            时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化:日期转换为字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);// 2020-08-02T21:56:50.223176300
        System.out.println(str1);//2020-08-02T21:56:50.2231763

        DateTimeFormatter isoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE;
        String str10 = isoLocalDate.format(localDateTime);
        System.out.println(str10);//2020-08-02    此时就只输出日期了。

        String str11 = DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
        System.out.println(str11);//2020-08-02T21:56:50.2231763     此时输出日期和时间

        //解析：字符串转换为日期  (注意：此时parse方法的参数格式必须符合
        //                         formatter对象要求的格式)
        //下面用TemporalAccessor这个接口接收的，LocalDateTime实现了这个接口，此处是多态思想
        TemporalAccessor parse = formatter.parse("2019-02-18T15:42:18.797");
        System.out.println(parse);//{},ISO resolved to 2019-02-18T15:42:18.797

//        方式二：
//        本地化相关的格式。如：ofLocalizedDateTime()
        //里面的参数有下面以下几种
//        FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :适用于LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        //格式化
        String str2 = formatter1.format(LocalDateTime.now());
        System.out.println(str2);//2020年8月2日 下午10:26:13


//      本地化相关的格式。如：ofLocalizedDate()
//      FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT : 适用于LocalDate
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        //格式化
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);//2020年8月2日


//       重点： 方式三：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //格式化  时间转为字符串
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4);//2020-08-02 22:33:56

        //解析    字符串转为时间
        TemporalAccessor accessor = formatter3.parse("2019-02-18 03:52:09");
        System.out.println(accessor);
//      {HourOfAmPm=3, SecondOfMinute=9, MilliOfSecond=0, MicroOfSecond=0, NanoOfSecond=0, MinuteOfHour=52},ISO resolved to 2019-02-18
    }

}
