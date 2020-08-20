package per.wsk.prac.$01;

/**
 * @Author weishaokang
 * @date 2020-07-30 18:15
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class FoodThread implements Runnable{

    private Person p;

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        synchronized (p) {
            p.notify();
            for (int i = 0; i < 3; i++) {
                System.out.println("吃第"+(i+1)+"个馒头");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public FoodThread(Person p) {
        this.p = p;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
}
