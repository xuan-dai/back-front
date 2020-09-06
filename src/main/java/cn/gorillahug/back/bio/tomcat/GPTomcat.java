package cn.gorillahug.back.bio.tomcat;

import cn.gorillahug.back.bio.tomcat.http.GPRequest;
import cn.gorillahug.back.bio.tomcat.http.GPResponse;
import cn.gorillahug.back.bio.tomcat.http.GPServlet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;

/**
 * @author daixuan
 * @version 2020/8/19 22:07
 */
@Slf4j
public class GPTomcat {
    private int port = 8080;
    private ServerSocket server;
    private Map<String, GPServlet> servletMap = Maps.newHashMap();

    private final Properties WEB_XML = new Properties();

    public static void main(String[] args) {
        new GPTomcat().start();
    }

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            WEB_XML.load(fis);

            for (Object k : WEB_XML.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url", "");
                    String url = WEB_XML.getProperty(key);
                    String className = WEB_XML.getProperty(servletName + ".className");

                    GPServlet obj = (GPServlet) Class.forName(className).newInstance();
                    servletMap.put(url, obj);
                }
            }
        } catch (Exception e) {
            log.error("GPTomcat error...", e);
        }
    }

    public void start() {
        init();

        try {
            server = new ServerSocket(this.port);
            while (true) {
                Socket client = server.accept();
                process(client);
            }

        } catch (Exception e) {
            log.error("GPTomcat start error...", e);
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream out = client.getOutputStream();

        GPRequest request = new GPRequest(is);
        GPResponse response = new GPResponse(out);

        String url = request.getUrl();

        if (servletMap.containsKey(url)) {
            servletMap.get(url).service(request, response);
        }else {
            response.write("404 - Not Found");
        }

        out.flush();
        out.close();

        is.close();
        client.close();
    }
}
