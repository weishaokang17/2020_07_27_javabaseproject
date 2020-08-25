package per.wsk.java.study03;

import org.junit.Test;

/**
 * @Author weishaokang
 * @date 2020-08-24 16:18
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 1. 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：<E1,E2,E3>
 * 2. 带有泛型的类，实例化后，操作原来泛型位置的结构必须与指定的泛型类型一致
 *
 * 3.  父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型：
 *       子类不保留父类的泛型：按需实现
 *           没有类型 擦除
 *           具体类型
 *       子类保留父类的泛型：泛型子类
 *           全部保留
 *           部分保留

            class Father<T1, T2> {
            }

            // 子类不保留父类的泛型
            // 1)没有类型 擦除
            class Son1 extends Father {// 等价于class Son extends Father<Object,Object>{
            }
            // 2)具体类型
            class Son2 extends Father<Integer, String> {
            }
            // 子类保留父类的泛型
            // 1)全部保留
            class Son3<T1, T2> extends Father<T1, T2> {
            }
            // 2)部分保留
            class Son4<T2> extends Father<Integer, T2> {
            }

            // 子类不保留父类的泛型
            // 1)没有类型 擦除
            class Son<A, B> extends Father{//等价于class Son extends Father<Object,Object>{
            }
            // 2)具体类型
            class Son2<A, B> extends Father<Integer, String> {
            }
            // 子类保留父类的泛型
            // 1)全部保留
            class Son3<T1, T2, A, B> extends Father<T1, T2> {
            }
            // 2)部分保留
            class Son4<T2, A, B> extends Father<Integer, T2> {
            }

 */
public class GenericTest {

    @Test
    public void test01(){
        //说明泛型中指定的类型也可以是接口
        User<UserDAO> user = new User<>();

    }
}
