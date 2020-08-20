package per.wsk.learn.study02;

/**
 * @Author weishaokang
 * @date 2020-07-27 11:20
 * @project 2020_07_27_javabaseproject
 * @description:


Thread中的常用方法：
 * 1. start():启动当前线程；调用当前线程的run()
 * 2. run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3. currentThread():静态方法，返回执行当前代码的线程
 * 4. getName():获取当前线程的名字
 * 5. setName():设置当前线程的名字
 * 6. yield():释放当前cpu的执行权
 * 7. join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才
 *           结束阻塞状态。
 * 8. stop():已过时。当执行此方法时，强制结束当前线程。
 * 9. sleep(long millitime):让当前线程“睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前
 *                          线程是阻塞状态。
 * 10. isAlive():判断当前线程是否存活
 *
 * 11.
 * 线程的优先级：
 * 1.
 * MAX_PRIORITY：10
 * MIN _PRIORITY：1
 * NORM_PRIORITY：5  -->默认优先级
 * 2.如何获取和设置当前线程的优先级：
 *   getPriority():获取线程的优先级
 *   setPriority(int p):设置线程的优先级
 *
 *   说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下
 *   被执行。并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。
 *
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        //① start和run两个方法不再说明

        //② currentThread():是一个静态方法，可以去源码大概看一下，
        // 底层调用C和C++，该返回执行当前代码的线程对象

        //③name name字段是Thread类中的属性，该属性有相应的set和get方法，详情可查看源码
        // Thread类中的getName和setName方法

        /*
        Thread有好多构造器，Thread类的空参构造器源码如下：
        里面又调了自己的另一个构造器，其中第三个参数就是设置Thread的name属性,
        值是"Thread-"拼上后面方法的返回值，一步步点进去源码看下，就知道平时Thread的子类
        用没有特别的声明构造器时，为什么子类的name是"Thread-0"、"Thread-1"等了
        public Thread() {
            this(null, null, "Thread-" + nextThreadNum(), 0);
        }

        除此外，Thread还有一个String类型的构造器，源码如下，该构造器就是自定义该线程对象的name属性。
        public Thread(String name) {
            this(null, null, name, 0);
        }
         */

        //④yield():yield英文中是放弃、让步的意思，该线程对象调用该方法后，
        // 该线程会释放当前cpu的执行权，交给cpu重新分配资源（见HelloThread类调用的该方法）

        //⑤join()：在线程a中调用线程b的join(),即b.join(),此时线程a就进入阻塞状态，
        // 直到线程b完全执行完以后，线程a才结束阻塞状态,由cpu重新分配资源，执行除b外其他的线程。

        //⑥stop():当执行此方法时，强制结束当前线程，当前线程结束不再执行。不过该方法已过时，不建议使用

        //⑦sleep(long millitime):是一个静态方法，让当前线程“睡眠”指定的millitime毫秒。
        // 在指定的millitime毫秒时间内，当前线程是阻塞状态,阻塞状态期间，CPU是可以去执行其他线程的.
        //令当前活动线程在指定时间段内放弃对CPU控制,使其他线程有机会被执行,时间到后当前线程重写
        //排队

        //⑧isAlive():判断当前线程的对象是否存活   当该线程对象执行完毕后，就不再存活了
        //    返回true表示该线程的对象仍然存活，返回false表示该线程对象已消亡

        //⑨.
        // 1. 线程的优先级：
        // MAX_PRIORITY：10
        // MIN _PRIORITY：1
        // NORM_PRIORITY：5  -->默认优先级
        // 线程优先级取值范围在1~10之间，1、5、10在Thread类中有相应的常量，剩下的几个数字就需要用数字表示了
        //
        // 2.如何获取和设置当前线程的优先级：
        // getPriority():获取线程的优先级
        // setPriority(int p):设置线程的优先级

        // 说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下
        // 被执行。并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。

        //3.线程创建时继承父线程的优先级


        //测试方法
        test();
    }

    private static void test() {

        HelloThread h1 = new HelloThread("Thread：1");

//        h1.setName("线程一");
        //设置分线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);

        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(9);

        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if(i == 20){
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

        //判断h1线程对象是否存活
        System.out.println(h1.isAlive());

    }
}
