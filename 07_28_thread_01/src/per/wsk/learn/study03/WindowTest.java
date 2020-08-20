package per.wsk.learn.study03;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:36
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class WindowTest {
    public static void main(String[] args) {
        Window window = new Window();

        new Thread(window,"窗口一").start();
        new Thread(window,"窗口二").start();
        new Thread(window,"窗口三").start();


    }
}
