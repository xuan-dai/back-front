package cn.gorillahug.back.bio.tomcat.http;

import java.util.Objects;

/**
 * 对底层socket 的输入和输出的封装
 * @author daixuan
 * @version 2020/8/19 21:44
 */
public abstract class GPServlet {

    public void service(GPRequest request, GPResponse response) throws Exception {
        if (Objects.equals(request.getMethod(), "GET")) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(GPRequest request, GPResponse response) throws Exception;

    public abstract void doPost(GPRequest request, GPResponse response) throws Exception;

}
