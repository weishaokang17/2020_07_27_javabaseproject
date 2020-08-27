package per.wsk.java.study04;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2019 上午 11:58
 *
 * apache下面的io这个jar包也提供了很多关于io流的方法。
 *
 */
public class FileUtilsTest {

    public static void main(String[] args) {
        File srcFile = new File("08_26_IO\\爱情与友情.jpg");
        File destFile = new File("08_26_IO\\爱情与友情2.jpg");

        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
