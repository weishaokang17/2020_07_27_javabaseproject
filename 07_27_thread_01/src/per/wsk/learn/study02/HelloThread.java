package per.wsk.learn.study02;

/**
 * @Author weishaokang
 * @date 2020-07-27 11:22
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class HelloThread extends Thread{

    /*
    点到Thread源码里面就知道了，Thread是普通类，不是抽象类，实现了Runnable接口，
    所以Thread必须重写Runnable中的run方法。
    因为当前类HelloThread继承Thread类，所以当前类不重写run方法也不会报错
     */




    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){

                //sleep方法会抛异常，此时只能try catch，不能throws，
                // 因为父类的Thread里面的run()方法就没有抛任何异常，根据重写的规则，子类的run()方法
                // 也不能抛异常

                /*
                if (i>50) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if(i % 20 == 0){
//                this.yield();
//            }
        }
    }

    //可设置线程对象名字的构造器
    public HelloThread(String name){
        super(name);
    }

}
