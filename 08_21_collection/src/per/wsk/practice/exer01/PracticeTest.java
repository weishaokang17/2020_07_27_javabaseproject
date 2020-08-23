package per.wsk.practice.exer01;

import org.junit.Test;

import java.util.*;

/**
 * @Author weishaokang
 * @date 2020-08-22 22:46
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class PracticeTest {

    /*
    请从键盘随机输入10个整数保存到List中，并先按倒序显示出来、再按照从大
    到小的顺序显示出来
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            list.add(num);
        }
        System.out.println(list);

        //倒序
        Collections.reverse(list);

        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });

        System.out.println(list);
    }

    /*
    .请把学生名与考试分数录入到集合中，并按分数显示前三名
     成绩学员的名字。
     */
    @Test
    public void test01(){
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put(97,"张三");
        map.put(74,"李四");
        map.put(99,"王五");
        map.put(86,"武松");
        map.put(100,"卢俊义");

//        map.keySet();
//        map.values();
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Object, Object>> item = entries.iterator();

        while (item.hasNext()){
            Map.Entry<Object, Object> next = item.next();
            System.out.println(next.getKey() + " ---> " + next.getValue());
        }
        //上面的输出是从小到大输出的
        /*
        74 ---> 李四
        86 ---> 武松
        97 ---> 张三
        99 ---> 王五
        100 ---> 卢俊义
         */

        System.out.println("------------------------------------");

        //这一步是将treeMap中的元素反转
        NavigableMap<Object, Object> map2 = map.descendingMap();
        Set<Map.Entry<Object, Object>> entrySet = map2.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entrySet.iterator();
        int i = 1;
        while (i<4 && iterator.hasNext()){
            Map.Entry<Object, Object> next = iterator.next();
            System.out.println(next.getKey() + " ---> " + next.getValue());
            i++;
        }
        /*
        100 ---> 卢俊义
        99 ---> 王五
        97 ---> 张三
         */
    }




}
