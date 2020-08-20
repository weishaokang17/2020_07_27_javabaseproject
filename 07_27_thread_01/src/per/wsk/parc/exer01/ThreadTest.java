package per.wsk.parc.exer01;

/**
 * @Author weishaokang
 * @date 2020-07-27 20:51
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setPriority(Thread.MAX_PRIORITY);
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        thread.start();

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
