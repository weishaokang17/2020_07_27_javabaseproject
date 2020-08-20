package per.wsk.parc.exer02;

/**
 * @Author weishaokang
 * @date 2020-07-27 21:00
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
