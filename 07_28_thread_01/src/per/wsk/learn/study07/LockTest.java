package per.wsk.learn.study07;

/**
 * @Author weishaokang
 * @date 2020-07-28 15:15
 * @project 2020_07_27_javabaseproject
 * @description:
 */


/**
 * 解决线程安全问题的方式二：Lock锁  --- JDK5.0新增  （方式一是synchronized方式）
 *
 * 1. 面试题：synchronized 与 Lock的异同？
 *   相同：二者都可以解决线程安全问题
 *   不同：
 *         ① Lock是显式锁，synchronized是隐式锁，出了作用域自动释放同步监视器，
 *   Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
 *         ② Lock只有代码块锁，synchronized有代码块锁和方法锁
 *         ③ 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有
 *          更好的扩展性（提供更多的子类）
 *
 * 2. 一般情况下解决线程安全问题的优先考虑顺序：
 * Lock  同步代码块（synchronized已经进入了方法体，分配了相应资源）
 *  同步方法（方法用synchronized修饰）
 *
 *
 *  面试题：如何解决线程安全问题？有几种方式
 *  两种，synchronized 与 lock
 */
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
