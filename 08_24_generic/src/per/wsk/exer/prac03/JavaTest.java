package per.wsk.exer.prac03;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author weishaokang
 * @date 2020-08-24 21:56
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class JavaTest {

    /*
    1.使用HashMap类实例化一个Map类型的对象m1，键（String类型）和值（int型）
    分别用于存储员工的姓名和工资，存入数据如下：
    张三——800元；李四——1500元；王五——3000元；
    2.将张三的工资更改为2600元
    3.为所有员工工资加薪100元；
    4.遍历集合中所有的员工
    5.遍历集合中所有的工资
     */
    @Test
    public void test01(){
        Map<String, Integer> m1 = new HashMap<>();
        m1.put("张三",800);
        m1.put("李四",1500);
        m1.put("王五",3000);

        m1.put("张三",2600);

        Set<Map.Entry<String, Integer>> entries = m1.entrySet();
        for (Map.Entry<String, Integer> entry:entries){
            entry.setValue(entry.getValue()+100);
        }

        for (Map.Entry<String, Integer> entry:entries){
            System.out.println("姓名:" + entry.getKey() + " 工资： " + entry.getValue());
        }

    }

}
