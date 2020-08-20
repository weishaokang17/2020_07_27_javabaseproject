package per.wsk.learn.study02;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:07
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class WindowTest {
    public static void main(String[] args) {
        Window t1 = new Window("窗口1");
        Window t2 = new Window("窗口2");
        Window t3 = new Window("窗口3");


//        t1.setName("窗口1");
//        t2.setName("窗口2");
//        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
