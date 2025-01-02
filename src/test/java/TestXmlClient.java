import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class TestXmlClient {

    public static void main(String[] args) {
        String url = "http://localhost:8080/orders-service/create-orders";

        String xmlBody = "<customers>\n" +
                "    <customer id=\"1\">\n" +
                "        <name>Maria</name>\n" +
                "        <birth-date>31/10/1982</birth-date>\n" +
                "        <orders>\n" +
                "            <order id=\"1\">\n" +
                "                <name>Milk</name>\n" +
                "                <date>25/07/2024</date>\n" +
                "                <count>2</count>\n" +
                "            </order>\n" +
                "            <order id=\"2\">\n" +
                "                <name>Bread</name>\n" +
                "                <date>25/07/2024</date>\n" +
                "                <count>1</count>\n" +
                "            </order>\n" +
                "            <order id=\"3\">\n" +
                "                <name>Eggs</name>\n" +
                "                <date>25/07/2024</date>\n" +
                "                <count>10</count>\n" +
                "            </order>\n" +
                "        </orders>\n" +
                "    </customer>\n" +
                "    <customer id=\"2\">\n" +
                "        <name>Anna</name>\n" +
                "        <birth-date>15/01/2000</birth-date>\n" +
                "        <orders>\n" +
                "            <order id=\"4\">\n" +
                "                <name>Juice</name>\n" +
                "                <date>26/07/2024</date>\n" +
                "                <count>4</count>\n" +
                "            </order>\n" +
                "            <order id=\"5\">\n" +
                "                <name>Water</name>\n" +
                "                <date>26/07/2024</date>\n" +
                "                <count>5</count>\n" +
                "            </order>\n" +
                "        </orders>\n" +
                "    </customer>\n" +
                "    <customer id=\"3\">\n" +
                "        <name>Antonio</name>\n" +
                "        <birth-date>22/06/1998</birth-date>\n" +
                "        <orders>\n" +
                "            <order id=\"6\">\n" +
                "                <name>Banana</name>\n" +
                "                <date>27/07/2024</date>\n" +
                "                <count>15</count>\n" +
                "            </order>\n" +
                "        </orders>\n" +
                "    </customer>\n" +
                "</customers>";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(url);
            postRequest.setHeader("Content-Type", "application/xml");
            StringEntity entity = new StringEntity(xmlBody);
            postRequest.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                if (response.getCode() == 200) {
                    System.out.println("200");
                } else if (response.getCode() == 500) {
                    System.out.println("500");
                }
            }
        } catch (Exception exception) {
            System.err.println("err " + exception.getMessage());
        }
    }
}
