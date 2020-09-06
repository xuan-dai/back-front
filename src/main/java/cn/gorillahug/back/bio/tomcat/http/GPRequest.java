package cn.gorillahug.back.bio.tomcat.http;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * input封装
 *
 * @author daixuan
 * @version 2020/8/19 21:47
 */
@Slf4j
public class GPRequest {

    @Getter
    private String method;
    @Getter
    private String url;

    public GPRequest(InputStream in) {
        try {
            StringBuilder content = new StringBuilder();
            byte[] buff = new byte[1024];
            int len = 0;
            if ((len = in.read(buff)) > 0) {
                String tmp = new String(buff, 0, len);
                content.append(tmp);
            }
            String line = content.toString().split("\\n")[0];
            String[] arr = content.toString().split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            log.error("read input stream error...", e);
        }
    }
}
