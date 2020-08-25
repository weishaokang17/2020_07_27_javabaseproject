package per.wsk.java.study02;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author weishaokang
 * @date 2020-08-25 16:28
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 相较于 FileReaderWriterTest中自己的练习。
 */
public class FileReaderWriterOwnPrac {

    @Test
    public void test01(){
        File file = new File("hello.txt");
        FileReader reader = null;
        try  {
            reader = new FileReader(file);

            int data = 0;
            while ((data = reader.read()) != -1){
                System.out.print((char)data);//需要将data类型强转成char类型
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test02(){
        File file = new File("hello.txt");
        FileReader reader = null;
        try {
            reader = new FileReader(file);

            StringBuilder sb = new StringBuilder();
            char[] arr = new char[5];
            int readCount = 0;

            while ((readCount=reader.read(arr)) != -1){
                sb.append(arr,0,readCount);
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    @Test
    public void test03(){
        File file = new File("字符输出.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file,true);

            String str = "还我河山，杀光俄罗斯民族!!!";

            fw.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //文件复制
    @Test
    public void test04(){
        File srcfile = new File("字符输出.txt");
        File sourcefile = new File("复制输出.txt");
        copyFile(srcfile,sourcefile);
    }

    public static void copyFile(File srcFile,File sourceFile){
        if (!srcFile.exists()) {
            return;
        }
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(srcFile);
            writer = new FileWriter(sourceFile);
            char[] arr = new char[10];
            int readLength = 0;
            while ((readLength = reader.read(arr)) != -1){
                writer.write(arr,0,readLength);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }



}
