package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class PositionDao {
    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private final String TABLE_NAME = "position";
    private final String ID_COLUMN = "account_id";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public PositionDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
    }

    public Optional<Position> findById(Integer id){
        Optional<Position> entity = Optional.empty();
        String selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE "+ID_COLUMN+"=?";
        try{
            entity = Optional.ofNullable((Position) jdbcTemplate.queryForObject(selectSQL, BeanPropertyRowMapper.newInstance(Position.class),id));
        } catch(IncorrectResultSizeDataAccessException e){
            logger.debug("Can't find trader ID: "+id,e);
        }
        return entity;
    }

    public boolean existsById(Integer id){
        String selectSQL = "SELECT "+ID_COLUMN+" FROM "+TABLE_NAME+" WHERE "+ID_COLUMN+"=?";
        try{
            jdbcTemplate.queryForObject(selectSQL, Integer.class,id);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public List<Position> findAll(){
        String selectSQL = "SELECT * FROM "+TABLE_NAME;
        return jdbcTemplate.query(selectSQL,BeanPropertyRowMapper.newInstance(Position.class));
    }
    public List<Position> findAllById(Iterable<Integer> ids){
        List<Position> targetList= new LinkedList();
        ids.forEach(id -> {
            targetList.add(findById(id).get());
        });
        return targetList;
    }

    public List<Position> findAllPositionsOfTraderId(Integer traderId) {
        if(this.count() == 0){
            return new ArrayList<Position>();
        }
        String selectSql = "SELECT * FROM "+TABLE_NAME+" WHERE trader_id=?";
        return jdbcTemplate.query(selectSql,BeanPropertyRowMapper.newInstance(Position.class),traderId);
    }

    public long count(){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM "+TABLE_NAME, Long.class);
    }
}
