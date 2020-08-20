package per.wsk.prac.$01;

/**
 * @Author weishaokang
 * @date 2020-07-30 18:14
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Producer implements Runnable{

    private Person p;

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        synchronized (this.p){
            while (true) {
                if (this.p.getToyNum() == 20) {
                    try {
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (this.p.getToyNum() >= 50 ) {
                    break;
                }

                this.p.setToyNum(p.getToyNum()+1);

                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产第" + p.getToyNum() + "个玩具");
            }
        }
    }

    public Producer(Person p) {
        this.p = p;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
}
