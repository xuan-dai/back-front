package cn.gorillahug.back.bio.tomcat.servlet;

import cn.gorillahug.back.bio.tomcat.http.GPServlet;
import cn.gorillahug.back.bio.tomcat.http.GPRequest;
import cn.gorillahug.back.bio.tomcat.http.GPResponse;

/**
 * @author daixuan
 * @version 2020/8/19 21:52
 */
public class FirstServlet extends GPServlet {

    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("this is the first servlet");
    }
}
