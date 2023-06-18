package fun.winterran.travel.dao;

import fun.winterran.travel.domain.Seller;

/**卖家数据层
 * @author xieren8iao
 * @create 2019/3/29 - 19:28
 */
public interface SellerDao {
    /**
     * 通过id查找卖家信息
     * @param rid
     * @return
     */
    public Seller findById(int rid);

}
