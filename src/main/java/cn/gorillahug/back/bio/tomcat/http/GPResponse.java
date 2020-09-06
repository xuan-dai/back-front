package cn.gorillahug.back.bio.tomcat.http;

import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;

/**
 * output 封装
 *
 * @author daixuan
 * @version 2020/8/19 21:47
 */
@Slf4j
public class GPResponse {

    private OutputStream out;

    public GPResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(s);
        out.write(s.getBytes());
    }

}
