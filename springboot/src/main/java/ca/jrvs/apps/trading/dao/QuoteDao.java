package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class QuoteDao implements CrudRepository<Quote,String> {
    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public QuoteDao(DataSource datasource){
        jdbcTemplate = new JdbcTemplate(datasource);
        jdbcInsert = new SimpleJdbcInsert(datasource).withTableName(TABLE_NAME);
    }


    @Override
    public Quote save(Quote quote){
        if(existsById(quote.getID())){
            int updatedRowNo = uptadetOne(quote);
            if(updatedRowNo != 1){
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        }
        else {
            addOne(quote);
        }
        return quote;
    }

    //insert one quote
    private void addOne(Quote quote){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = jdbcInsert.execute(parameterSource);
        if(row != 1)
            throw new IncorrectResultSizeDataAccessException("Failed to insert",1,row);
    }

    //update one quote
    private int uptadetOne(Quote quote){
        String update_SQL = "UPDATE "+TABLE_NAME+" SET last_price=?, bid_price=?, bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
        return jdbcTemplate.update(update_SQL,quote.getLastPrice(),quote.getBidPrice(),quote.getBidSize(),quote.getAskPrice(),quote.getAskSize(),quote.getTicker());
    }

    //insert/update all quotes in iterable
    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
        iterable.forEach(quote -> save(quote));
        return iterable;
    }

    //find quote by ticker
    @Override
    public Optional<Quote> findById(String ticker) {
        try{
            Quote targetQuote = jdbcTemplate.queryForObject("SELECT * FROM "+TABLE_NAME+" WHERE ticker=?", (resultSet, rowNum) -> {
                Quote newQuote = new Quote();
                newQuote.setID(resultSet.getString(1));
                newQuote.setLastPrice(resultSet.getDouble(2));
                newQuote.setBidPrice(resultSet.getDouble(3));
                newQuote.setBidSize(resultSet.getInt(4));
                newQuote.setAskPrice(resultSet.getDouble(5));
                newQuote.setAskSize(resultSet.getInt(6));
                return newQuote;
            },ticker);
            return Optional.of(targetQuote);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    //return true/false if quote exists in DB
    @Override
    public boolean existsById(String ticker) {
        try{
            jdbcTemplate.queryForObject("SELECT ticker FROM "+TABLE_NAME+" WHERE ticker=?", String.class,ticker);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //returns all quotes
    @Override
    public Iterable<Quote> findAll() {
        return jdbcTemplate.query("SELECT * FROM "+TABLE_NAME, (resultSet, rowNum) -> {
            Quote newQuote = new Quote();
            newQuote.setID(resultSet.getString(1));
            newQuote.setLastPrice(resultSet.getDouble(2));
            newQuote.setBidPrice(resultSet.getDouble(3));
            newQuote.setBidSize(resultSet.getInt(4));
            newQuote.setAskPrice(resultSet.getDouble(5));
            newQuote.setAskSize(resultSet.getInt(6));
            return newQuote;
        });
    }

    //unsupported find all
    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Not implemented/required");
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM "+TABLE_NAME, Long.class);
    }

    @Override
    public void deleteById(String ticker) {
        int result = jdbcTemplate.update("DELETE FROM "+TABLE_NAME+" WHERE ticker=?",ticker);
        if(result != 1)
            logger.error("Failed to delete by id "+ticker);
    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("Not implemented/required");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("Not implemented/required");
    }

    //delete all from quote table
    @Override
    public void deleteAll() {
        int result = jdbcTemplate.update("DELETE FROM "+TABLE_NAME);
        if(result != 1)
            logger.error("Failed to delete all ");
    }
}
