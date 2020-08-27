package per.wsk.java.study06;

import org.junit.Test;

import java.io.*;

/**
 * @Author weishaokang
 * @date 2020-08-26 15:49
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 缓冲流自己的练习
 */
public class BufferedTestOwn {


    @Test
    public void test01(){
        copyPhoto("爱情与友情2.jpg","爱情与友情3.jpg");
    }

    public static void copyPhoto(String path1,String path2){
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path1));
            outputStream = new BufferedOutputStream(new FileOutputStream(path2));

            byte[] arr = new byte[20];
            int readLength = 0;
            while ((readLength = inputStream.read(arr))!=-1){
                outputStream.write(arr,0,readLength);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void test02(){
        copyFile("dbcp.txt","dbcp2.txt");
    }


    public static void copyFile(String srcPath,String sourcePath){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(srcPath));
            writer = new BufferedWriter(new FileWriter(sourcePath));

            /* 写法一：
            char[] arr = new char[20];
            int readLength = 0;
            while ((readLength = reader.read(arr)) != -1){
                writer.write(arr,0,readLength);
            }
            */

            String readContent = null;
            //缓冲输入流BufferedReader的readLine()方法返回读取的一行，返回值是一个字符串。
            while ((readContent = reader.readLine())!=null){
                writer.write(readContent);
                writer.newLine();//换行
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
