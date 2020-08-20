package per.wsk.learn.study05;

/**
 * @Author weishaokang
 * @date 2020-07-28 19:35
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class ThreadTest {
    /**
     执行下面的main方法会依次输出：
        线程一睡觉前
        主线程凯文加内特
        User{name='凯文加内特'}
        线程一睡觉后
        线程二睡觉前
        线程二睡觉后

     说明：针对同步代码块的同步监听器，不仅仅针对同一个同步代码块或同一个同步方法
     是单线程，两个或多个同步代码块以及两个或多个同步方法，只要他们的同步监听器是
     一样的，那么这么多个同步代码块和同步方法合在一起是单线程的。
     main方法的最后两行能在前面输出，说明同步监听器这个对象在被其他地方当成同步监听器使用的时候，
     不影响这个对象的正常使用。

     例如：下面testThread方法两个同步代码块，这两个同步代码块用的都是main方法中
     新建的user对象这个同步监听器，那么不仅这两个代码块各自不能被多个线程执行，这两个
     代码块也不能同时执行。

     所以，开发中，用同步监听器时，尽量保证当前的同步方法或同步代码块用的
     同步监听器，这个同步监听器没有被其他同步代码块和同步方法当作同步监听器使用。
     否则，相当于和另一个同步代码块或同步方法用的都是同一个同步监听器，这样会相互影响。
     */
    public static void main(String[] args) {
        User user = new User("凯文加内特");

        testThread(user);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程"+user.getName());
        System.out.println(user);
    }



    public static void testThread(User user){

        new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized (user) {
                    System.out.println(Thread.currentThread().getName()+"睡觉前");
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"睡觉后");
                }
            }
        },"线程一").start();


        new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized (user) {
                    System.out.println(Thread.currentThread().getName() + "睡觉前");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "睡觉后");
                }
            }
        },"线程二").start();
    }



}

class User{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
