package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Security_Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class Security_OrderDao extends JdbcCrudDao<Security_Order> {
    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private final String TABLE_NAME = "security_order";
    private final String ID_COLUMN = "id";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public Security_OrderDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
    }


    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIDColumnName() {
        return ID_COLUMN;
    }

    @Override
    Class<Security_Order> getEntityClass() {
        return Security_Order.class;
    }

    @Override
    public int updateOne(Security_Order entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(Security_Order security_order) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Security_Order> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void deleteByAccountId(Integer accountId) {
        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE account_id=?";
        getJdbcTemplate().update(deleteSql,accountId);
    }
}
