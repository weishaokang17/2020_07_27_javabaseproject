package per.wsk.learn.study12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author weishaokang
 * @date 2020-07-29 21:38
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * pool 在英文中是 池塘、池子的意思
 *
 * 关于线程池的讲解详见印象笔记
 */
public class ThreadPool {

    //创建线程池
    public static ThreadPoolExecutor threadPool= new ThreadPoolExecutor(
            5,
            10,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        ThreadArray threadArray = new ThreadArray();
        threadPool.submit(threadArray);

        for (int i = 1; i < 8; i++) {
            threadPool.execute(new Window());
        }

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("线程"+ finalI);
                    System.out.println(Thread.currentThread().getName()+
                            " *********************** ");
                }
            });
        }

        //调用关闭线程池的方法，但此时如果线程池内还有线程在运行，此时不会关闭
        //程序会等到线程池的所有线程都运行结束了再关闭
        threadPool.shutdown();
    }
}
