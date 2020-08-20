package per.wsk.learn.study06;

/**
 * @Author weishaokang
 * @date 2020-07-28 14:49
 * @project 2020_07_27_javabaseproject
 * @description:
 */



/**
 * 演示线程的死锁问题
 *
 * 1.死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，
 * 都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *
 * 2.说明：
 * 1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2）我们使用同步时，要避免出现死锁。
 *
 * 3.避免死锁
 * 专门的算法、原则
 * 尽量减少同步资源的定义
 * 尽量避免嵌套同步
 *
 * 同一个包下面DeadLock02类是死锁的另一个举例演示
 */

public class DeadLock01 {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        //下面两个线程，线程1握着s1这个同步监视器，然后睡眠100毫秒，
        //线程2握着s2这个同步监视器，然后睡眠100毫秒，
        //线程1睡眠完毕后，需要拿到s2这个同步监视器，才能执行后面的代码，
        // 但此时s2这个同步监视器被线程2拿着，所以需要线程2执行完毕后释放s2这个同步监视器
        // 才行，但线程2要想执行完毕，执行后面的代码需要先拿到s1这个同步监视器，此时s1这个
        //同步监视器被线程1拿着，需要等线程1运行结束线程2才能继续执行。
        // 这就是死锁，有点类似死循环

        new Thread(){
            @Override
            public void run() {

                synchronized (s1){

                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }


                }

            }
        }.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }


                }



            }
        }).start();
    }
}
