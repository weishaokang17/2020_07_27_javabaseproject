package per.wsk.java.study02;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author weishaokang
 * @date 2020-08-25 11:26
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 *
 * 注意：
 *    在方法中写的相对路径， 相对路径是相对于方法的调用者来说的。
 *    如果在junit的测试方法中，相对路径是相当于当前mudule来说的。
 *    如果在main方法中，相对路径是相当于当前工程来说的。
 *
 */
public class FilePractice {

    /*
    1. 利用File构造器，new 一个文件目录file
       1)在其中创建多个文件和目录
       2)编写方法，实现删除file中指定文件的操作
       删除操作在test02()方法
     */
    @Test
    public void test01() throws IOException {
        File file = new File("Folder");
        file.mkdir();

        File file1 = new File("Folder\\1.txt");
        File file2 = new File("Folder\\2.dox");
        File file3 = new File("Folder\\3.jpg");
        File file4 = new File("Folder\\4.xls");

        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();
        file4.createNewFile();

    }

    @Test
    public void test02() throws IOException{
        File file = new File("Folder");

        File file1 = new File("Folder\\1.txt");
        File file2 = new File("Folder\\2.dox");
        File file3 = new File("Folder\\3.jpg");
        File file4 = new File("Folder\\4.xls");

        file1.delete();
        file2.delete();
        file3.delete();
        file4.delete();

        file.delete();
    }

    /*
    2. 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
     */
    @Test
    public void test03(){
        File file = new File("F:\\photos");
        getJPGs(file);
    }

    public static void getJPGs(File file){
        if (file.exists()) {
            if (file.isFile() && file.getPath().endsWith(".jpg")) {
                System.out.println(file);
            } else if (file.isDirectory()){
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f:files){
                        getJPGs(f);
                    }
                }
            }
        }
    }

    /*
    3. 遍历指定目录所有文件名称，包括子文件目录中的文件。
       拓展1：并计算指定目录占用空间的大小
       拓展2：删除指定文件目录及其下的所有文件
     */
    @Test
    public void test04(){
        File file = new File("F:\\百度网盘下载");
        getAllDirAndFile(file);

        long spaceCount = getSpaceCount(file);
        System.out.println(spaceCount);

        File file1 = new File("F:\\迅雷下载\\尚硅谷_宋红康_JDBC核心技术(2019新版)");
        delFileAndDir(file1);
    }

    public static void getAllDirAndFile(File file){
        if (!file.exists()) {
            return;
        }
        System.out.println(file);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for(File f : files){
                    getAllDirAndFile(f);
                }
            }
        }
    }

    public static long getSpaceCount(File file){
        long count = 0;
        if (!file.exists()) {
            return count;
        }
        if (file.isFile()) {
            return file.length();
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for(File f : files){
                    count+=getSpaceCount(f);
                }
            }
        }
        return count;
    }


    public static void delFileAndDir(File file){
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for(File f : files){
                    delFileAndDir(f);
                }
            }
            file.delete();
        }
    }
}
