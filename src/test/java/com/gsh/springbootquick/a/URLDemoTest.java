package com.gsh.springbootquick.a;

import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Create By GSH on .
 */
public class URLDemoTest {

    @Test
    void URL() throws IOException {
        URL url = new URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp02%2F1Z9191GF921C-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639809628&t=d3edf4f35b2351fff325c3f15afeb442");

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream("pic.jpg");
        byte[] bytes = new byte[1024];
        int len;
        while((len = is.read(bytes)) != -1){
            fos.write(bytes, 0, len);
        }

        fos.close();
        is.close();
        urlConnection.disconnect();

    }
}
