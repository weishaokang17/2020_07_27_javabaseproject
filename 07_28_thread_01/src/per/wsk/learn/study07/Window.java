package per.wsk.learn.study07;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author weishaokang
 * @date 2020-07-28 15:10
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Window implements Runnable{

    private int ticket = 100;
    //1.实例化ReentrantLock （如果当前window不是实现的runnable接口，是继承的Thread类，
    // 此时下面的lock对象应该声明成static修饰的）
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try{
                //2.调用锁定方法lock()
                lock.lock();

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：售票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                //3.调用解锁方法：unlock()  //担心方法出现异常，故把解锁方法放到finally中
                lock.unlock();
            }

        }
    }
}
