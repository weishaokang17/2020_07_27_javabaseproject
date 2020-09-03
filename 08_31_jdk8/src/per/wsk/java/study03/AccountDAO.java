package per.wsk.java.study03;

import java.util.Date;

/**
 * @Author weishaokang
 * @date 2020-09-01 11:45
 * @project 2020_07_27_javabaseproject
 * @description:
 */
//public interface AccountDAO {
public interface AccountDAO<T> {

//    void excuteData(String str1, Date date);

//    String excuteData(String str1, Date date);


    /*
    泛型方法的声明规范是：

    方法修饰符（包含权限修饰符和非权限修饰符） <T> 返回值类型 方法名(参数列表){
            方法体
    }

    但如果接口中出现上面这种写法，就不能使用lambda表达式了。
    lambda表达式也可以用泛型方法，但此时的泛型方法前面必须去掉<T>，然后把<T>加到接口名的后面。
     */
    String excuteData(T str1,Date date);


}
