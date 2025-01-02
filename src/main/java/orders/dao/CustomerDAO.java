package orders.dao;

import orders.models.Customer;
import orders.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class CustomerDAO {
    private static final Logger logger = LogManager.getLogger(CustomerDAO.class);
    private static final String QUERY_CUSTOMERS = "INSERT INTO customers VALUES (?, ?, ?)";
    private static final String QUERY_ORDERS = "INSERT INTO orders VALUES (?, ?, ?, ?, ?)";
    private static final String QUERY_TRUNCATE_CUSTOMERS = "TRUNCATE customers";
    private static final String QUERY_TRUNCATE_ORDERS = "TRUNCATE orders";
    private static final String DB_URL = "jdbc:mysql://localhost/customers_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            connection.setAutoCommit(false);
            Statement stmtTruncateTables = connection.createStatement();
            boolean deleteCustomers = stmtTruncateTables.execute(QUERY_TRUNCATE_CUSTOMERS);
            boolean deleteOrders = stmtTruncateTables.execute(QUERY_TRUNCATE_ORDERS);
            if (deleteOrders || deleteCustomers) {
                connection.commit();
                connection.close();
                stmtTruncateTables.close();
            }
        } catch (Exception exception) {
            logger.error("Error while trying to delete customers and orders ", exception);
        }
    }

    public boolean save(List<Customer> customers) {
        try (
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement preparedStatementCustomers = connection.prepareStatement(QUERY_CUSTOMERS);
                PreparedStatement preparedStatementOrders = connection.prepareStatement(QUERY_ORDERS)
        ) {
            connection.setAutoCommit(false);
            for (Customer customer : customers) {
                preparedStatementCustomers.setInt(1, customer.getId());
                preparedStatementCustomers.setString(2, customer.getName());
                preparedStatementCustomers.setString(3, customer.getBirthDate());
                preparedStatementCustomers.execute();
                List<Order> orders = customer.getOrders();
                for (int i = 0; i < customer.getOrders().size(); i++) {
                    Order order = orders.get(i);
                    preparedStatementOrders.setInt(1, order.getId());
                    preparedStatementOrders.setString(2, order.getName());
                    preparedStatementOrders.setString(3, order.getPurchasedDate());
                    preparedStatementOrders.setInt(4, order.getCount());
                    preparedStatementOrders.setInt(5, customer.getId());
                    preparedStatementOrders.execute();
                }
            }
            connection.commit();
            return true;
        } catch (Exception exception) {
            logger.error("Error while saving all information ", exception);
            return false;
        }
    }
}
