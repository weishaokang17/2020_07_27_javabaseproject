package per.wsk.learn.study04;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:48
 * @project 2020_07_27_javabaseproject
 * @description:
 */

/**
 释放锁的操作
 ①当前线程的同步方法、同步代码块执行结束。
 ②当前线程在同步代码块、同步方法中遇到break、return终止了该代码块、
 该方法的继续执行。
 ③当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导
 致异常结束。
 ④当前线程在同步代码块、同步方法中执行了线程对象的wait()方法，当前线
 程暂停，并释放锁。


 不会释放锁的操作
 ①线程执行同步代码块或同步方法时，程序调用Thread.sleep()、
 Thread.yield()方法暂停当前线程的执行
 ②线程执行同步代码块时，其他线程调用了该线程的suspend()方法将该线程
 挂起，该线程不会释放锁（同步监视器）。
 注意：应尽量避免使用suspend()和resume()来控制线程，而且这两个方法都是过期方法
 */


/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 */
public class WindowTest {
    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();

        window1.start();
        window2.start();
        window3.start();

        try {
            Thread.currentThread().sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //下面的window.start()方法会报异常，因为一个线程对象只能start一次。
        if (!window1.isAlive()){
            window1.start();
            System.out.println("---");
        }
    }
}
