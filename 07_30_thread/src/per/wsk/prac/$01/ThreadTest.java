package per.wsk.prac.$01;

/**
 * @Author weishaokang
 * @date 2020-07-30 9:59
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * 模拟一个人生产50个玩具，每200毫秒生产一个，
 * 当生产到第20个时加入每秒吃1个馒头，
 * 共吃完3个后在接着生产的多线程。
 */
public class ThreadTest {

    public static void main(String[] args) {
        Person person = new Person();

        Producer producer = new Producer(person);

        new Thread(producer).start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new FoodThread(person)).start();

        try {
            Thread.sleep(14000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Person person1 = new Person();
        testProduceToy(person1);
    }

    private static void testProduceToy(Person person1) {
        //生产
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                synchronized (person1){
                    while (true) {
                        if (person1.getToyNum() == 20) {
                            try {
                                person1.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        if (person1.getToyNum() == 50) {
                            break;
                        }

                        person1.setToyNum(person1.getToyNum()+1);

                        try {
                            Thread.sleep(200L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("生产第" + person1.getToyNum() + "个玩具");
                    }
                }
            }
        }).start();


        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                synchronized (person1) {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("吃第" + (i + 1) + "个馒头");
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    person1.notify();
                }
            }
        }).start();



    }
}
