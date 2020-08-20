package per.wsk.learn.study04;

/**
 * 创建多线程的方式二：实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现类去实现Runnable中的抽象方法：run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5. 通过Thread类的对象调用start()
 *
 *
 * 比较创建线程的两种方式。
 * 开发中：优先选择：实现Runnable接口的方式
 * 原因：
 * 1. 实现的方式没有类的单继承性的局限性（如study03包下面的Window继承了Thread类，
 *   那就无法继承别的类了，但study05包下面的Window1实现了Runnable接口，那么此时
 *   Window1可以还继承别的类）
 * 2. 实现的方式更适合来处理多个线程有共享数据的情况，继承Thread方式要想共享数据，要把
 *   共享的数据被static修饰。
 *
 * 联系：public class Thread implements Runnable Thread类本身也实现了Runnable接口
 * 相同点：两种方式都需要重写run(),将线程要执行的逻辑声明在run()中。
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:34
 */
//1. 创建一个实现了Runnable接口的类
class MThread implements Runnable{

    //2. 实现类去实现Runnable中的抽象方法：run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

        }
    }
}


public class ThreadTest1 {
    public static void main(String[] args) {
        //3. 创建实现类的对象
        MThread mThread = new MThread();
        //4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(mThread);//Thread类中有参数是Runnable的构造器

        t1.setName("线程1");

        //5. 通过Thread类的对象调用start():
        // ① 启动线程
        // ②调用当前线程的run()-->当前线程是Thread，所以调用的是Thread类中的run()方法，但Thread中的
        // run()方法源码如下所示：target是Thread中的一个Runnable类型的属性，详情看源码。
        // 上面通过Thread类中参数是Runnable的构造器创建的对象，这个构造器的参数就是给当前Thread对象的
        //target属性赋值，当前Thread对象的target属性是mThread这个MThread类的对象，所以按照下面的源码
        //会调用MThread类中的run()方法

        /*
        @Override
        public void run() {
            if (target != null) {
                target.run();
            }
        }
        */
        t1.start();

        //再启动一个线程，遍历100以内的偶数
        Thread t2 = new Thread(mThread);
        t2.setName("线程2");
        t2.start();
    }

}
