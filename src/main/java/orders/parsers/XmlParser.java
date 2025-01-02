package orders.parsers;

import orders.models.Customer;
import orders.models.Customers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

public class XmlParser implements Parser {
    private static final Logger logger = LogManager.getLogger(XmlParser.class);

    @Override
    public List<Customer> parse(String xmlContent) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader reader = new StringReader(xmlContent);

            Customers customers = (Customers) unmarshaller.unmarshal(reader);
            return customers.getCustomers();
        } catch (JAXBException exception) {
            logger.error("Error parsing XML content", exception);
            throw new RuntimeException("Error in XML Parser");
        }
    }
}
