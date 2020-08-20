package per.wsk.parc.exer02;

/**
 * @Author weishaokang
 * @date 2020-07-27 20:59
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
