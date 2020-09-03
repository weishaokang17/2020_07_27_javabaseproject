package per.wsk.java.study04;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 *
 * 常用的方法：ofNullable(T t)
 *            orElse(T t)
 *
 * @author shkstart
 * @create 2019 下午 7:24
 */
public class OptionalTest {


    /*
    Optional 这个类中，有一个属性名叫value，这个属性的类型是泛型，新建Optional对象的时候
    就需要指定这个Optional对象的value属性的类型。
     */

    /*
    三种创建Optional对象的方法：

    Optional.of(T t) : 创建一个 Optional 实例，指定这个实例中的value属性的类型是T，并且
                        这个Optional实例的value属性不能为空；

    Optional.empty() : 创建一个Optional实例，这个Optional实例里面的value属性是null，
                        且没有指定value属性所属的类型。

    Optional.ofNullable(T t) :  创建一个Optional实例，这个Optional实例里面的value属性可以是null，
                        也可以非空，但不管是不是null，都必须指定value属性所属的类型。
     */
    @Test
    public void test1(){
        Girl girl = new Girl();
//        girl = null;
        //of(T t):保证t是非空的   如果girl是null，下面的这行代码会报空指针异常
        Optional<Girl> optionalGirl = Optional.of(girl);

        Optional<String> empty = Optional.empty();

    }

    @Test
    public void test2(){
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);

        System.out.println(optionalGirl);//输出： Optional[Girl{name='null'}]

        Girl girl2 = null;

        Optional<Girl> optionalGirl2 = Optional.ofNullable(girl2);
        System.out.println(optionalGirl2); //输出： Optional.empty


        //orElse(T t1):如果单前的Optional内部封装的t是非空的，则返回内部的t.
        //如果内部的t是空的，则返回orElse()方法中的参数t1.
        Girl girl1 = optionalGirl.orElse(new Girl("赵丽颖"));
        System.out.println(girl1);

    }

    @Test
    public void test3(){
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName(boy);
        System.out.println(girlName);

    }

    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }

    @Test
    public void test4(){
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }

    //优化以后的getGirlName():
    public String getGirlName1(Boy boy){
        if(boy != null){
            Girl girl = boy.getGirl();
            if(girl != null){
                return girl.getName();
            }
        }
        return null;
    }


    //使用Optional类的getGirlName():
    public String getGirlName2(Boy boy){

        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽热巴")));

        Girl girl = boy1.getGirl();

        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //girl1一定非空
        Girl girl1 = girlOptional.orElse(new Girl("古力娜扎"));

        return girl1.getName();
    }

    @Test
    public void test5(){
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("苍老师"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);

    }



}
