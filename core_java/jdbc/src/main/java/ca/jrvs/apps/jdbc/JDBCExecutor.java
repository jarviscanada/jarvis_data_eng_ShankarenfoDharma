package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        final Logger lggr = LoggerFactory.getLogger(JDBCExecutor.class);

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost","hplussport","postgres","password");
        try{
//            Connection connection = dcm.getConnection();
//            CustomerDAO customerDAO = new CustomerDAO(connection);
//            Customer customer = new Customer();
//            customer.setFirstName("George");
//            customer.setLastName("Washington");
//            customer.setEmail("george.washington@wh.gov");
//            customer.setPhone("(555) 555-6543");
//            customer.setAddress("1234 Main St");
//            customer.setCity("Mount Vernon");
//            customer.setState("VA");
//            customer.setZipCode("22121");
//            customerDAO.create(customer);

            Connection conn = dcm.getConnection();
            Statement statement = conn.createStatement();
            OrderDAO orderDAO = new OrderDAO(conn);
            Order orderResult = orderDAO.findById(1000);
            lggr.debug(orderResult.toString());
        } catch(SQLException e){
            lggr.error("Error connecting: "+e.getMessage());
        }
    }
}
