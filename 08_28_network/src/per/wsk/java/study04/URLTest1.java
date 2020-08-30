package per.wsk.java.study04;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author shkstart
 * @create 2019 下午 4:54
 *
 * URLConnection：
 * 表示到URL所引用的远程对象的连接。当与一个URL建立连接时，
 * 首先要在一个 URL 对象上通过方法 openConnection() 生成对应的 URLConnection
 * 对象。如果连接过程失败，将产生IOException.
 * URL netchinaren = new URL ("http://www.atguigu.com/index.shtml");
 * URLConnectonn u = netchinaren.openConnection( );
 *
 *
 * URLConnectonn类中的一些常用方法
 * public Object getContent( ) throws IOException
 * public int getContentLength( )
 * public String getContentType( )
 * public long getDate( )
 * public long getLastModified( )
 * public InputStream getInputStream( )throws IOException
 * public OutputSteram getOutputStream( )throws IOException
 *
 *
 *
 * URI，是uniform resource identifier，统一资源标识符，用来唯一的标识一个
 * 资源。而URL是uniform resource locator，统一资源定位符，它是一种具体
 * 的URI，即URL可以用来标识一个资源，而且还指明了如何locate这个资源。
 * 而URN，uniform resource name，统一资源命名，是通过名字来标识资源，
 * 比如mailto:java-net@java.sun.com。也就是说，URI是以一种抽象的，高层
 * 次概念定义统一资源标识，而URL和URN则是具体的资源标识的方式。URL
 * 和URN都是一种URI。
 *
 *
 * 在Java的URI中，一个URI实例可以代表绝对的，也可以是相对的，只要它符
 * 合URI的语法规则。而URL类则
 * 不仅符合语义，还包含了定位该资源的信息，
 * 因此它不能是相对的。
 *
 */
public class URLTest1 {

    public static void main(String[] args) {

        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {

            URL url = new URL("http://localhost:8080/examples/beauty.jpg");
            //创建一个HttpURLConnection对象
            urlConnection = (HttpURLConnection) url.openConnection();
            //开始连接
            urlConnection.connect();

            //读取http://localhost:8080/examples/beauty.jpg这个资源
            is = urlConnection.getInputStream();

            //把读取的资源写到到下面这个流里面，相当于做了下载的操作。
            fos = new FileOutputStream("day10\\beauty3.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

            //上面的操作其实就是把tomcat中的/examples/beauty.jpg下载到
            //本地。要保证tomcat服务器启动，并且路径存在。
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }






    }
}
