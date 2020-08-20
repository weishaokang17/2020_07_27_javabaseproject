package per.wsk.learn.study08;

/**
 * 线程通信的例子：使用两个线程打印 1-100。线程1, 线程2 交替打印
 *
 * 涉及到的三个方法：
 * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器。
 * notify():一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个。
 * notifyAll():一旦执行此方法，就会唤醒所有被wait的线程。
 *
 * 说明：
 * 1.wait()，notify()，notifyAll()三个方法必须使用在同步代码块或同步方法中
 *      （即调用这三个方法必须在同步代码块或同步方法的里面）。
 * 2.wait()，notify()，notifyAll()三个方法的调用者必须是同步代码块或同步方法中的同步监视器。
 *    否则，会出现IllegalMonitorStateException异常
 * 3.wait()，notify()，notifyAll()三个方法是定义在java.lang.Object类中的。
 *  （原因：因为上面规定只有同步代码块或同步方法中的同步监视器才能调用这三个方法，同步监听器可能
 *  是任何引用类型的对象，要想让任何一个引用类型的对象都能调用这三个方法，就只能把这三个方法定义到
 *  Object类中了）。
 *
 *
 * 假设线程a和线程b，线程a和线程b都执行一段同步代码块，该代码块的同步监听器是对象obj,若
 * a先进去，此时线程b在外面等候，若a在同步代码块中执行了obj.wait方法，此时b进入同步代码块，若b执行notify()方法
 * 则线程a被唤醒。    如果还有一个线程c也在该同步代码块中执行了wait方法，此时b执行notify()方法只能唤醒线程a或
 * 线程c的一个，哪个优先级高就唤醒哪个，如果优先级相同就随机唤醒一个。如果b调用的不是notify()方法，调用的是
 * notifyAll()方法，此时线程a和c都会被唤醒。
 *
 *
 * 面试题：sleep() 和 wait()的异同？
 * 1.相同点：一旦执行方法，都可以使得当前的线程进入阻塞状态。
 * 2.不同点：
 *          1）两个方法声明的位置不同：Thread类中声明sleep() , Object类中声明wait()。
 *              sleep()是静态方法，wait()不是静态方法
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用。 wait()必须使用在同步
 *              代码块或同步方法中
 *          3）关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，
 *          sleep()不会释放锁，wait()会释放锁。
 *
 * @author shkstart
 * @create 2019-02-15 下午 4:21
 */
class Number implements Runnable{
    private int number = 1;
    private Object obj = new Object();

    @Override
    public void run() {

        while(true){

            synchronized (obj) {

                obj.notify();

                if(number <= 100){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用如下wait()方法的线程进入阻塞状态
                        obj.wait();
                        /*
                        假设此时t1被阻塞，此时t1释放同步监听器，此时t2会进到同步代码块中，
                        一进来就执行notify()方法，此时t1被唤醒，虽然t1被唤醒，但因为此时
                        同步监听器在t2手中，所以t1被唤醒后，t1不会继续执行同步代码块内的后面的代码。
                        直到t2执行到同步代码块内的wait()方法时，此时t2释放同步监听器，然后t1继续执行
                        同步代码块内的后面的代码，执行完成后，由于此时t2还在wait,此时是t1再进到
                        同步代码块中，进来后先唤醒t2，t2被唤醒，但不会执行同步代码块内的后面的代码。因为
                        此时t1还占用着同步监听器，直到t1执行wait方法释放同步监听器.....
                        如此循环
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    break;
                }
            }

        }

    }
}


public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number,"线程一");
        Thread t2 = new Thread(number,"线程二");

        t1.start();
        t2.start();



        //主线程先睡5秒
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**************************************");


        // 创建锁对象 保证唯一
        Object obj = new Object();
        //创建一个顾客对象，作为一个线程运行
        new Thread() {
            @Override
            public void run() {
                //使用同步技术，避免出现安全问题，
                //保证等待和唤醒的线程只有一个进行
                synchronized(obj) {
                    System.out.println("顾客点餐");
                    //调用wait方法等待，放弃cpu的试用期，进入WAITIING状态
                    try {
                        obj.wait();
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    //被唤醒之后执行的代码
                    System.out.println("顾客取餐，开始吃");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                //暂停5秒，5秒后通知顾客
                try {
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                //同样需要使用同步技术，保证只有一个线程执行
                synchronized(obj) {
                    System.out.println("店家已完成，请顾客取餐");
                    obj.notify();
                }
            }
        }.start();
    }
}
