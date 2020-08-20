package per.wsk.java.study02;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;


/**
 * @Author weishaokang
 * @date 2020-08-18 11:15
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Log4jTest {

    //获取Logger对象的实例
    static Logger logger = Logger.getLogger(Log4jTest.class);


    @Test
    public void test01(){
        //使用默认的配置信息，不需要写log4j.properties
        BasicConfigurator.configure();
        //设置日志输出级别为WARN，这将覆盖配置文件中设置的级别，
        // 只有日志级别高于WARN的日志才输出
        logger.setLevel(Level.WARN);
        logger.debug("这是debug");
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
        logger.fatal("这是fatal");

        /*
        控制台输出：
        这是warn
        这是error
        这是fatal
         */


        /*
        如果没有调用 BasicConfigurator.configure() 方法，
        则运行该方法会报错，因为 Log4j 框架在运行的时候会加载项目路径下的
         log4j.properties 配置文件。而此时的项目中是没有该文件的。
         如果配置文件的名称不是 log4j.properties，则可以通过
          PropertyConfigurator.configure(String configFilename) 指定配置文件的名称。
         */
    }


    /*
    首先创建一个HTML格式的格式化器（HTMLLayout），然后创建文件输出端（FileAppender）
    指定输出端的格式为 HTML 格式，这里我指定输出路径为 E 盘下的 out.html 文件。
    然后通过 logger.addAppender(appender) 将文件输出端加载到 Logger 中。
    运行 test02方法，在 E 盘下会生成一个 out.html 文件
     */
    @Test
    public void test02(){
        BasicConfigurator.configure();
        HTMLLayout layout = new HTMLLayout();
        try {
            FileAppender appender = new FileAppender(layout, "E:\\out.html", false);
            logger.addAppender(appender);
            //设置日志输出级别为info，这将覆盖配置文件中设置的级别，只有日志级别高于WARN的日志才输出
            logger.setLevel(Level.WARN);
            logger.debug("这是debug");
            logger.info("这是info");
            logger.warn("这是warn");
            logger.error("这是error");
            logger.fatal("这是fatal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    使用代码的方式设置 Logger 的输出格式，
    这样在每个要输出日志的类上都得设置一遍配置未免也太麻烦。
    更加方便的方法是，只需要在项目路径下新建 log4j.properties 配置文件，
    并配置日志的输出格式等信息，Log4J 框架会自动的加载配置文件，
    并将配置信息设置到 Logger 中。
     */
    @Test
    public void test03(){
        logger.debug("这是debug");
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
        logger.fatal("这是fatal");

        String str = "波士顿凯尔特人总冠军";
        logger.info(this.getClass().getName()+" : test03: "+str);

        int num1 = 10;
        int num2 = 0;
        try {
            int result = num1 / num2;
        } catch (Exception e) {
            logger.error(this.getClass().getName()+" : test03: "+e.getMessage(),e);
        }
    }



}
