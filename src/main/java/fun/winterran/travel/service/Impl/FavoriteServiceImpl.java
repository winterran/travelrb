package fun.winterran.travel.service.Impl;

import fun.winterran.travel.dao.FavoriteDao;
import fun.winterran.travel.dao.impl.FavoriteDaoImpl;
import fun.winterran.travel.domain.Favorite;
import fun.winterran.travel.service.FavoriteService;

/**收藏业务层
 * @author xieren8iao
 * @create 2019/3/29 - 21:59
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public Boolean isFavorite(int uid,int rid) {
        Favorite favorite = favoriteDao.findByUidAndRid(uid, rid);

        return favorite!=null;//如果有值，表示收藏过，否则反之
    }

    @Override
    public void addFavorite(int rid, int uid) {
        favoriteDao.addFavorite(rid, uid);
    }
}
