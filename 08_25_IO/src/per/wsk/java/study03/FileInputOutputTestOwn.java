package per.wsk.java.study03;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author weishaokang
 * @date 2020-08-26 11:47
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * 字节流的自己练习
 *
 */
public class FileInputOutputTestOwn {

    @Test
    public void test01(){
        File srcVideo = new File("F:\\尚硅谷Java核心基础_2019年版\\30天全套课程\\day26_IO流\\学习视频\\19-尚硅谷-Java语言高级-缓冲流课后练习2.avi");
        File sourceVideo = new File("C:\\Users\\Administrator\\Desktop\\复制的视频.avi");
        copyVideo(srcVideo,sourceVideo);
    }


    public static void copyVideo(File srcVideo,File sourceVideo){
        if (!srcVideo.exists()) {
            return;
        }
        FileInputStream reader = null;
        FileOutputStream writer = null;
        try {
            reader = new FileInputStream(srcVideo);
            writer = new FileOutputStream(sourceVideo);
            byte[] arr = new byte[1024];
            int readLength = 0;
            while ((readLength = reader.read(arr)) != -1){
                writer.write(arr,0,readLength);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
