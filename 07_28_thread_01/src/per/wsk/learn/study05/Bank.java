package per.wsk.learn.study05;

/**
 * @Author weishaokang
 * @date 2020-07-28 12:12
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 */
public class Bank {

    private Bank(){}

    private static Bank instance = null;

    //生产环境两个用户操作，相当于两个线程

    public static Bank getInstance(){
        //方式一：效率稍差，后续每调一次这个方法都要排队等待
//        synchronized (Bank.class) {
//            if(instance == null){
//
//                instance = new Bank();
//            }
//            return instance;
//        }

        //方式二：效率更高
        if(instance == null){

            synchronized (Bank.class) {
                if(instance == null){

                    instance = new Bank();
                }

            }
        }
        return instance;
    }

}
