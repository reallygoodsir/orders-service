package orders.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import orders.models.Customer;
import orders.models.Customers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class JsonParser implements Parser {
    private static final Logger logger = LogManager.getLogger(JsonParser.class);

    @Override
    public List<Customer> parse(String jsonContent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Customers customers = objectMapper.readValue(jsonContent, Customers.class);
            return customers.getCustomers();
        } catch (IOException exception) {
            logger.error("Error parsing JSON content", exception);
            throw new RuntimeException("Error in JSON Parser");
        }
    }
}

