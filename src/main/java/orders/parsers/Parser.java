package orders.parsers;

import orders.models.Customer;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Customer> parse(String content) throws IOException;

}
