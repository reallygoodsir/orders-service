package orders.servlets;

import orders.dao.CustomerDAO;
import orders.models.Customer;
import orders.parsers.JsonParser;
import orders.parsers.XmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class OrdersServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(OrdersServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String contentType = request.getContentType();
            logger.info("contentType \n{}", contentType);
            String body = getBody(request);
            logger.info("Body \n{}", body);

            List<Customer> customers;
            if ("application/xml".equalsIgnoreCase(contentType)) {
                XmlParser xmlParser = new XmlParser();
                customers = xmlParser.parse(body);
            } else if ("application/json".equalsIgnoreCase(contentType)) {
                JsonParser jsonParser = new JsonParser();
                customers = jsonParser.parse(body);
            } else {
                throw new Exception("not json/xml");
            }
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.deleteAll();
            boolean isSaved = customerDAO.save(customers);
            if (isSaved) {
                response.setStatus(200);
            } else {
                response.setStatus(500);
            }
        } catch (Exception exception) {
            logger.error("Error while processing the xml/json", exception);
            response.setStatus(500);
        }
    }
    
    public static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try (BufferedReader bufferedReader = request.getReader()) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException exception) {
            throw new IOException("Error reading the request payload", exception);
        }
        return stringBuilder.toString();
    }
}
