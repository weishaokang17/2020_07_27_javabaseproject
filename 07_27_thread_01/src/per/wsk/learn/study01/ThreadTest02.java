package per.wsk.learn.study01;

/**
 * @Author weishaokang
 * @date 2020-07-27 10:57
 * @project 2020_07_27_javabaseproject
 * @description:
 * 创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 */
public class ThreadTest02 {

    public static void main(String[] args) {
        //方式一：
        new Thread01().start();
        new Thread02().start();

        //方式二:匿名类
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName()+"\t"+i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName()+"\t"+i);
                    }
                }
            }
        }.start();
    }
}



class Thread01 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        }
    }
}

class Thread02 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        }
    }
}
