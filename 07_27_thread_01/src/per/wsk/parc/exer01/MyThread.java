package per.wsk.parc.exer01;

/**
 * @Author weishaokang
 * @date 2020-07-27 20:49
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(getName()+"\t"+i);
            }
            if (i == 50) {
                try {
                    this.join();
                    //假设当前线程是b线程，在b线程中调用a.join()方法
                    //此时b线程会阻塞，要等a线程结束才再去执行b线程
                    //但上面的写法，this.join()。当前线程阻塞，还要等当前线程
                    //运行结束才继续执行当前线程，此时当前线程会一直处于
                    //阻塞状态。这种情况有点类似死锁，死锁后面会讲到。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
