package per.wsk.java.study05OtherClass;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 其他常用类的使用
 * 1.System
 * 2.Math
 * 3.BigInteger 和 BigDecimal
 *
 * @author shkstart
 * @create 2019 下午 6:23
 */
public class OtherClassTest {

    /*
    System类代表系统，系统级的很多属性和控制方法都放置在该类的内部。
    该类位于java.lang包。
    一、 由于该类的构造器是private的，所以无法创建该类的对象，也就是无法实
    例化该类。其内部的成员变量和成员方法都是static的，所以也可以很方便
    的进行调用。
    二、 成员变量
    System类内部包含in、out和err三个成员变量，分别代表标准输入流
    (键盘输入)，标准输出流(显示器)和标准错误输出流(显示器)。

    不难理解平时用的 new Scanner(System.in) 和 System.out.println()这两个一个表示输入、一个表示输出了。

    三、成员方法
    ① native long currentTimeMillis()： 该方法的作用是返回当前的计算机时间，
    时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。
    ② void exit(int status)： 该方法的作用是退出程序。其中status的值为0代表正常退出，非零代表
    异常退出。使用该方法可以在图形界面编程中实现程序的退出功能等。
    ③ void gc()： 该方法的作用是请求系统进行垃圾回收。至于系统是否立刻回收，则
    取决于系统中垃圾回收算法的实现以及系统执行时的情况。
    ④ String getProperty(String key)： 该方法的作用是获得系统中属性名为key的属性对应的值。系统中常见
    的属性名以及属性的作用如下表所示：

        java.version     java运行时环境版本（jdk版本）
        java.home         java安装路径
        os.name            操作系统名称
        os.version          操作系统版本
        user.name           用户的账户名称
        user.home           用户的主目录
        user.dir            用户的当前工作目录
     */
    @Test
    public void test1() {
        String javaVersion = System.getProperty("java.version");
        System.out.println("java的version:" + javaVersion);//11.0.6

        String javaHome = System.getProperty("java.home");
        System.out.println("java的home:" + javaHome);
        //D:\apps\java\jdk1.11-OS-64\jdk-11.0.6_windows-x64_bin\jdk-11.0.6

        String osName = System.getProperty("os.name");
        System.out.println("os的name:" + osName);
        //Windows 10

        String osVersion = System.getProperty("os.version");
        System.out.println("os的version:" + osVersion);//10.0

        String userName = System.getProperty("user.name");
        System.out.println("user的name:" + userName);//Administrator

        String userHome = System.getProperty("user.home");
        System.out.println("user的home:" + userHome);//C:\Users\Administrator

        String userDir = System.getProperty("user.dir");
        System.out.println("user的dir:" + userDir);
        //E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\2020_07_27_javabaseproject\08_02_commonclasss
    }


    /*
    java.lang.Math提供了一系列静态方法用于科学计算。其方法的参数和返回
    值类型一般为double型。

    abs 绝对值
    acos,asin,atan,cos,sin,tan 三角函数
    sqrt 平方根
    pow(double a,doble b) a的b次幂
    log 自然对数
    exp e为底指数
    max(double a,double b)
    min(double a,double b)
    random() 返回0.0到1.0的随机数
    long round(double a) double型数据a转换为long型（四舍五入）
    toDegrees(double angrad) 弧度—>角度
    toRadians(double angdeg) 角度—>弧度
     */
    @Test
    public void test02(){
        Math.random();//[0,1)
        //Math类的方法中，相比较而言这个方法最常用，返回一个0到1之间的double数字，包含0不包含1
    }



    /*
    BigInteger类：

     Integer类作为int的包装类，能存储的最大整型值为2的31次方减1，Long类也是有限的，
     Long能存储的最大数字为2的63次方减1。如果要表示再大的整数，不管是基本数据类型还是他们的包装类
    都无能为力，更不用说进行运算了。BigInteger可以存储很大很大的整数，如光年等等。

    一、java.math包的BigInteger可以表示不可变的任意精度的整数。BigInteger 提供
    所有 Java 的基本整数操作符的对应物，并提供 java.lang.Math 的所有相关方法。
    另外，BigInteger 还提供以下运算：模算术、GCD 计算、质数测试、素数生成、
    位操作以及一些其他操作。
    二、
     ①构造器  BigInteger(String val)：根据字符串构建BigInteger对象
     ②常用方法
        1. public BigInteger abs()：返回此 BigInteger 的绝对值的 BigInteger。
        2. BigInteger add(BigInteger val) ：返回其值为 (this + val) 的 BigInteger
        3. BigInteger subtract(BigInteger val) ：返回其值为 (this - val) 的 BigInteger
        4. BigInteger multiply(BigInteger val) ：返回其值为 (this * val) 的 BigInteger
        5. BigInteger divide(BigInteger val) ：返回其值为 (this / val) 的 BigInteger。整数
        相除只保留整数部分。
        6. BigInteger remainder(BigInteger val) ：返回其值为 (this % val) 的 BigInteger。
        7. BigInteger[] divideAndRemainder(BigInteger val)：返回包含 (this / val) 后跟
        (this % val) 的两个 BigInteger结果 的数组。
        8. BigInteger pow(int exponent) ：返回其值为 (this的exponent次方) 的 BigInteger。
     */
    @Test
    public void test3() {
        BigInteger bi = new BigInteger("1243324112234324324325235245346567657653");

        System.out.println(bi);//1243324112234324324325235245346567657653
//
    }


    /*
    BigDecimal类:
    一、 一般的Float类和Double类可以用来做科学计算或工程计算，但在商业计算中，
要求数字精度比较高，故用到java.math.BigDecimal类。
    二、 BigDecimal类支持不可变的、任意精度的有符号十进制定点数。
    三、
    1. 构造器
        ① public BigDecimal(double val)
        ② public BigDecimal(String val)
    2. 常用方法
        ① public BigDecimal add(BigDecimal augend)
        ② public BigDecimal subtract(BigDecimal subtrahend)
        ③ public BigDecimal multiply(BigDecimal multiplicand)
        ④ public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
     */
    @Test
    public void test04(){
        BigDecimal bd = new BigDecimal("12435.351");
        BigDecimal bd2 = new BigDecimal("11");

//        System.out.println(bd.divide(bd2));//这行报异常，因为除不尽，也没有指定四舍五入。
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        //下面这行方法是指定保留小数点后25位，第25位四舍五入
        System.out.println(bd.divide(bd2, 25, BigDecimal.ROUND_HALF_UP));

    }

}
