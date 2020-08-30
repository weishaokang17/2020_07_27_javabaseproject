package per.wsk.java.study01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File类的使用
 *
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，
 *    必须使用IO流来完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 *
 *
 *
 *
 * @author shkstart
 * @create 2019 下午 4:05
 */
public class FileTest {
    /*
    1.如何创建File类的实例
        ① File(String filePath):
        以pathname为路径创建File对象，可以是绝对路径或者相对路径，如果
        pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。
        ② File(String parentPath,String childPath)
        以parent为父路径，child为子路径创建File对象。
        ③ File(File parentFile,String childPath)
        根据一个父File对象和子文件路径创建File对象

    2.
    相对路径：相较于某个路径下，指明的路径。
    绝对路径：包含盘符在内的文件或文件目录的路径

    3.路径分隔符 ：路径中的每级目录之间用一个路径分隔符隔开。


    ①路径分隔符和系统有关：

      windows系统用: \     例如：D:\workspace_idea1\JavaSenior\day08\he.txt
         但因为java中\代表转义字符，如\t表示tab键，\n表示回车键。
         因此，如果java代码中表示路径直接用\，会编译器会误认为是转义符。
         所以，java中，实际表示\这个符号的是\\这个符号。

        （其实windows系统用/也能识别出来，不过最好还是用\\）

      UNIX和URL使用“/”来表示

    ② Java程序支持跨平台运行，因此路径分隔符要慎用。
    ③为了解决这个隐患，File类提供了一个常量：

       public static final String separator。根据操作系统，动态的提供分隔符。

       例如，下面的test1()方法中的file2对象，值是
       D:\workspace_idea1\JavaSenior\day08\he.txt
       可以改为 D:+ File.separator + workspace_idea1+ File.separator +
            JavaSenior+ File.separator + day08+ File.separator + he.txt
       即把\变成 File.separator这个File类中的常量，这种写法可以兼容Windows和unix系统。
     */
    @Test
    public void test1(){
        /*
        下面这几个File类的对象，构造器中的参数表示的路径中实际都不存在，但
        运行仍然通过，输出的是文件路径，此时文件路径不存在但运行不报错是因为，
        此时的操作只是在java内存层面的，并没有进行硬盘层面的一些操作。

        File类重写了toString()方法，输出的是类中的path属性。
         */

        //构造器1：
        File file1 = new File("hello.txt");//相对于当前module
        File file2 =  new File("D:\\workspace_idea1\\JavaSenior\\day08\\he.txt");

        System.out.println(file1);//hello.txt
        System.out.println(file2);//D:\workspace_idea1\JavaSenior\day08\he.txt

        //构造器2：
        File file3 = new File("D:\\workspace_idea1","JavaSenior");
        System.out.println(file3);//D:\workspace_idea1\JavaSenior

        //构造器3：
        File file4 = new File(file3,"hi.txt");
        System.out.println(file4);//D:\workspace_idea1\JavaSenior\hi.txt
    }


    /*
    public String getAbsolutePath()：获取绝对路径
    public String getPath() ：获取路径
    public String getName() ：获取名称
    public String getParent()：获取上层文件目录路径。若无，返回null
    public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
    public long lastModified() ：获取最后一次的修改时间，毫秒值

    如下的两个方法适用于文件目录：
    public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
    public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
     */
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("E:\\JavaWorkSpace\\Intellij_IDEAWrokSpace\\IntelliJ_IDEA 2020.1.1_64bit_workSpace\\2020_07_27_javabaseproject\\08_24_IO\\hello.txt");

        System.out.println(file1.getAbsolutePath());
        /*
        输出绝对路径：
        E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\
        2020_07_27_javabaseproject\08_24_IO\hello.txt
         */
        System.out.println(file1.getPath());
        //输出文件路径(创建文件对象时用的什么路径此处返回就是什么路径，即创建对象用的相对路径
        // 此处就是相对路径。这一点不要死记，要看源码)
        // hello.txt
        System.out.println(file1.getName());//输出文件名字    hello.txt
        System.out.println(file1.getParent());//null
        //输出上层文件目录路径， 因为该文件本身不存在，所以输出null
        System.out.println(file1.length());//文件长度(字节数)  0
        System.out.println(file1.lastModified());//输出文件最后一次的修改时间，距离1970年的毫秒值
        //文件不存在就返回0

        System.out.println("---------------------------");

        System.out.println(file2.getAbsolutePath());
        //E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\
        //        2020_07_27_javabaseproject\08_24_IO\hello.txt
        System.out.println(file2.getPath());
        //E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\
        //        2020_07_27_javabaseproject\08_24_IO\hello.txt
        System.out.println(file2.getName());//hello.txt
        System.out.println(file2.getParent());
        // E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\
        //        2020_07_27_javabaseproject\08_24_IO
        System.out.println(file2.length());//13
        System.out.println(file2.lastModified());// 1598282001347
    }

    @Test
    public void test3(){
        File file = new File("E:\\JavaWorkSpace\\Intellij_IDEAWrokSpace\\IntelliJ_IDEA 2020.1.1_64bit_workSpace\\2020_07_27_javabaseproject\\08_24_IO");
//        File file = new File("hello.txt");
        String[] list = file.list();
        for(String s : list){
            System.out.println(s);
            /*
            08_24_IO.iml
            hello.txt
            src
             */
        }

        System.out.println();

        File[] files = file.listFiles();
        for(File f : files){
            System.out.println(f);
            /*
            E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\2020_07_27_javabaseproject\08_24_IO\08_24_IO.iml
            E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\2020_07_27_javabaseproject\08_24_IO\hello.txt
            E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\2020_07_27_javabaseproject\08_24_IO\src

             */
        }

    }


    @Test
    public void testOwn01(){
        //相对路径,当前目录的上一层目录中的log4j.properties这个文件
        File file = new File("..\\log4j.properties");
        System.out.println(file.length());//1411

        //相对路径，当前所在路径的上一层路径
        File file1 = new File("..\\");

        System.out.println(file1.getPath());//..
        //输出绝对路径
        System.out.println(file1.getAbsolutePath());
        //E:\JavaWorkSpace\Intellij_IDEAWrokSpace\IntelliJ_IDEA 2020.1.1_64bit_workSpace\2020_07_27_javabaseproject\08_24_IO\..
        System.out.println(file1.getName());//..
        System.out.println(file1.getParent());//null
        System.out.println(file1.length());//4096
        System.out.println(file1.lastModified());//1598277722005

        System.out.println("---------------------");

        File[] files = file1.listFiles();
        for (File f:files) {
            System.out.println(f);
            /*
            ..\.git
            ..\.gitignore
            ..\.idea
            ..\07_27_thread_01
            ..\07_28_thread_01
            ..\07_30_thread
            ..\07_31_commonClasss
            ..\08_02_commonclasss
            ..\08_03_enum
            ..\08_04_annotation
            ..\08_05_collection
            ..\08_19_collection
            ..\08_21_collection
            ..\08_24_generic
            ..\08_24_IO
            ..\2020_07_27_javabaseproject.iml
            ..\jdbc.properties
            ..\log4j.properties
            ..\out
             */
        }

        System.out.println("---------------------");

        String[] list = file1.list();
        for (String path: list) {
            System.out.println(path);
            /*
            .git
            .gitignore
            .idea
            07_27_thread_01
            07_28_thread_01
            07_30_thread
            07_31_commonClasss
            08_02_commonclasss
            08_03_enum
            08_04_annotation
            08_05_collection
            08_19_collection
            08_21_collection
            08_24_generic
            08_24_IO
            2020_07_27_javabaseproject.iml
            jdbc.properties
            log4j.properties
            out
             */
        }
    }




    /*
    public boolean renameTo(File dest):把文件重命名为指定的文件路径。
    其实这个方法类似于剪切。
     比如：file1.renameTo(file2)为例：
        要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。

     file1.renameTo(file2)，如果file1和file2路径相同，只是文件名不同，此时相当于
     给文件进行了重命名。
     */
    @Test
    public void test4(){
        /*
        下面就相当于把当前路径下的hello.txt文件剪切到D:\io 路径下，
        剪切后并把文件改名叫hi.txt
        这个方法要想成功，需要保证D:\io这个路径是存在的。
        即剪切文件时如果发现目标文件夹不存在不会自动创建文件夹。
         */
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");

        boolean renameTo = file1.renameTo(file2);
        System.out.println(renameTo);

    }

    /*
    public boolean isDirectory()：判断是否是文件目录（即是否是文件夹）
    public boolean isFile() ：判断是否是文件
    public boolean exists() ：判断是否存在
    public boolean canRead() ：判断是否可读
    public boolean canWrite() ：判断是否可写
    public boolean isHidden() ：判断是否隐藏（是否是隐藏文件）

    public void setWritable():传true或false,设置是否可写
    public void setReadable():传true或false,设置是否可读

    如果创建的File对象的路径不存在，以下方法全部返回false
     */
    @Test
    public void test5(){
        File file1 = new File("hello.txt");
        file1 = new File("hello1.txt");

        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();

        File file2 = new File("d:\\io");
        file2 = new File("d:\\io1");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }
    /*
    创建硬盘中对应的文件或文件目录

    public boolean createNewFile() ：创建文件。返回文件创建是否成功，
                                    若文件存在，则不创建，返回false
    public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。
                          如果此文件目录的上层目录不存在，也不创建。
    public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。
                           如果上层文件目录不存在，一并创建

    删除磁盘中的文件或文件目录
    public boolean delete()：删除文件或者文件夹
    删除注意事项：Java中的删除不走回收站，即删除后直接就从硬盘中清理了，不会放的回收站。

     */
    @Test
    public void test6() throws IOException {
        //相对路径   当前模块下的hi.txt
        File file1 = new File("hi.txt");
        if(!file1.exists()){
            //文件的创建
            file1.createNewFile();
            System.out.println("创建成功");
        }else{//文件存在
            file1.delete();
            System.out.println("删除成功");
        }

//        File file2 = new File("fileFloder\\hi.txt");
        File file2 = new File("F:\\photos\\fileFloder\\hi.txt");
        //由于file2的txt文件的所在文件夹路径就是不存在的，下面创建file2这个文件是没有创建成功的。
        file2.createNewFile();
        //由于file2的txt文件的所在文件夹路径就是不存在的，所以调用exists()方法会报异常。
        if (file2.exists()) {
            file2.delete();
            System.out.println("删除成功");
        } else {
            file2.createNewFile();
            System.out.println("创建成功");
        }
    }

    @Test
    public void test7() throws IOException {
        //文件目录的创建
        File file1 = new File("fileFloder\\files");

        boolean mkdir = file1.mkdir();
        if(mkdir){
            System.out.println("创建成功1");
        }


        boolean mkdir1 = file1.mkdirs();
        if(mkdir1){
            System.out.println("创建成功2");
        }
        //要想把一个文件夹删除成功，文件目录下不能有子目录或文件

        //当 fileFloder\files里面有file4这个文件时，此时file3会删除失败。
        File file2 = new File("fileFloder\\files\\1.txt");
        file2.createNewFile();
        System.out.println(file2.delete());

        //以下两行代码，file3删除失败，说明当文件夹下的目录全都是空文件夹，此时改文件夹一样会删除失败
        File file3 = new File("fileFloder");
        System.out.println(file3.delete());

        //先把要删除的文件夹下的所有子文件夹和文件，包含空的子文件夹，把这些全部删掉，
        //再删除当前文件夹才能删除成功。
        System.out.println(file1.delete());
        System.out.println(file3.delete());
    }
}
