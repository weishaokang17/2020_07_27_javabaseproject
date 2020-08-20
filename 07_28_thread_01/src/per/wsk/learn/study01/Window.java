package per.wsk.learn.study01;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:02
 * @project 2020_07_27_javabaseproject
 * @description:
 */


/**
 * 例子：创建三个窗口卖票，总票数为100张.使用实现Runnable接口的方式
 *
 * 1.问题：卖票过程中，出现了重票、错票 -->出现了并发时的线程安全问题
 * 2.问题出现的原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
 * 3.如何解决：当一个线程a在操作ticket的时候，其他线程不能参与进来。直到线程a操作完ticket时，其他
 *            线程才可以开始操作ticket。这种情况即使线程a出现了阻塞，也不能被改变。
 *
 *
 * 4.在Java中，我们通过同步机制，来解决线程的安全问题。
 *
 *      方式一：同步代码块
 *
 *   synchronized(同步监视器){
 *      //需要被同步的代码
 *
 *   }
 *  说明：1.操作共享数据的代码，即为需要被同步的代码。
 *              -->同步的代码块不能包含代码多了，也不能包含代码少了。
 *              如果包含少了，会出现线程的安全问题，如果包含的多了，可能会出现不符合开发需求
 *              的问题，例如下面如果把while(true)也包含到synchronized里面，相当于只有一个
 *              窗口买票
 *
 *       2.共享数据：多个线程共同操作的变量。比如：ticket就是共享数据。
 *       3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *          重点：要求多个线程必须要共用同一把锁。
 *
 *       补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *
 *
 *      方式二：同步方法。
 *        如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 *        详见study03和study04这两个包
 *
 *  5.同步的方式，解决了线程的安全问题。---好处
 *    操作同步代码时，只能有一个线程参与，其他线程等待。
 *    相当于是一个单线程的过程，效率低。---局限性    但为了保障线程安全问题，必须这么做
 *
 */
public class Window implements Runnable{

    private int ticket = 100;
    //    Object obj = new Object();
//    Dog dog = new Dog();
    @Override
    public void run() {
        //如果同步监视器用下面这行场景的obj这个对象，不符合同步监视器的需求
//        Object obj = new Object();
        while(true){
            synchronized (this){//此时的this:唯一的Window1的对象   //方式二：synchronized (dog) {

                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //下面这行就不能直接写getName了，因为当前Window类没有继承Thread，故当前对象没有getName()方法
                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);


                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

