package fun.winterran.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**抽取servlet
 * @author xieren8iao
 * @create 2019/3/27 - 20:10
 */

public class BaseServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //完成方法分发 /user/add  /user/find
        //1.获取请求/访问的路径
        String uri=request.getRequestURI();  // /user/add
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);  // 截取 /user/add 中的add
        //3.获取方法对象Method，this代表调用方法的类
        //4.调用/执行方法
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);            method.invoke(this, request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将对象传入并以json写回客户端
     */
    public void WriteValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }
    public String WriteAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
           return mapper.writeValueAsString(obj);

    }
}
