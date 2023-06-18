package fun.winterran.travel.web.servlet;

import fun.winterran.travel.domain.Category;
import fun.winterran.travel.service.CategoryService;
import fun.winterran.travel.service.Impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xieren8iao
 * @create 2019/3/27 - 20:11
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    //业务对象
    private CategoryService service=new CategoryServiceImpl();

    /**
     * 查找所有分类信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查询
        List<Category> list = service.findAll();
        //封装成json
        /*response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), list);*/
        WriteValue(list, response);
//        System.out.println(list);

    }

}
