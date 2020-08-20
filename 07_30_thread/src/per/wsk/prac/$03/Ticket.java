package per.wsk.prac.$03;

/**
 * @Author weishaokang
 * @date 2020-07-30 11:25
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Ticket implements Runnable{

    public int ticket = 100;

    @Override
    public void run() {
        while (true){
            synchronized (this){
                notifyAll();

                if (this.ticket<=0){
                    break;
                }

                System.out.println(Thread.currentThread().getName() + "卖出倒数第" + this.ticket-- + "张票");

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
