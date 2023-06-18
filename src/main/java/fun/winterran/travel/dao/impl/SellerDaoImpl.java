package fun.winterran.travel.dao.impl;

import fun.winterran.travel.dao.SellerDao;
import fun.winterran.travel.domain.Seller;
import fun.winterran.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author xieren8iao
 * @create 2019/3/29 - 19:28
 */
public class SellerDaoImpl implements SellerDao {
   private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    public Seller findById(int id) {

        String sql="select * from tab_seller where sid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
