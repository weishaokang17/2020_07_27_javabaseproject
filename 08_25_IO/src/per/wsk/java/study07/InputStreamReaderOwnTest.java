package per.wsk.java.study07;

import org.junit.Test;

import java.io.*;

/**
 * @Author weishaokang
 * @date 2020-08-26 21:11
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 转换流的自己练习
 */
public class InputStreamReaderOwnTest {

    @Test
    public void test01() throws IOException {
        FileInputStream inputStream = new FileInputStream("hello.txt");
        FileOutputStream outputStream = new FileOutputStream("hello1.txt");

        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,"UTF-8");

        char[] arr = new char[10];
        int readLength = 0;
        while ((readLength = reader.read(arr)) != -1){
            writer.write(arr,0,readLength);
        }

        reader.close();
        writer.close();

    }

    @Test
    public void test02(){
        File file = new File("hello1.txt");
        file.delete();
    }

}
