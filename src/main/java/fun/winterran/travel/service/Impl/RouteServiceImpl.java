package fun.winterran.travel.service.Impl;

import fun.winterran.travel.dao.FavoriteDao;
import fun.winterran.travel.dao.RouteDao;
import fun.winterran.travel.dao.RouteImgDao;
import fun.winterran.travel.dao.SellerDao;
import fun.winterran.travel.dao.impl.FavoriteDaoImpl;
import fun.winterran.travel.dao.impl.RouteDaoImpl;
import fun.winterran.travel.dao.impl.SellerDaoImpl;
import fun.winterran.travel.domain.PageBean;
import fun.winterran.travel.domain.Route;
import fun.winterran.travel.domain.RouteImg;
import fun.winterran.travel.domain.Seller;
import fun.winterran.travel.service.RouteService;

import java.util.List;

/**路线业务层
 * @author xieren8iao
 * @create 2019/3/28 - 14:15
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    private RouteImgDao imgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    /**
     * 分页功能
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //创建PageBean对象
        PageBean<Route> pageBean=new PageBean<Route>();
        //设置PageBean对象属性
        int totalCount=dao.findTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int totalPage=(totalCount % pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        //从start开始查询
        int start=(currentPage-1)*pageSize;
        List<Route> list=dao.findByPage(cid, start, pageSize,rname);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据id查找route
     * @param rid
     * @return
     */
    @Override
    public Route find(int rid) {
        //查找route对象
        Route route=dao.findByRid(rid);
        //查找旅游路线的图片
        List<RouteImg> routeImgeList=imgDao.findByRid(rid);
        //查找旅游路线的卖家信息
        Seller seller=sellerDao.findById(route.getSid());

        route.setRouteImgList(routeImgeList);
        route.setSeller(seller);
        //查找收藏的次数
        int count=favoriteDao.findCountByRid(rid);
        route.setCount(count);
        return route;
    }
}
