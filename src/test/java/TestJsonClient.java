import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class TestJsonClient {
    public static void main(String[] args) {
        String url = "http://localhost:8080/orders-service/create-orders";
        String jsonBody = "{\n" +
                "  \"customers\":[\n" +
                "    {\n" +
                "      \"id\":1,\n" +
                "      \"name\":\"Maria\",\n" +
                "      \"birthDate\":\"31/10/1982\",\n" +
                "      \"orders\":[\n" +
                "        {\n" +
                "          \"id\":1,\n" +
                "          \"name\":\"Milk\",\n" +
                "          \"purchasedDate\":\"25/07/2024\",\n" +
                "          \"count\":2\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":2,\n" +
                "          \"name\":\"Bread\",\n" +
                "          \"purchasedDate\":\"25/07/2024\",\n" +
                "          \"count\":1\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":3,\n" +
                "          \"name\":\"Eggs\",\n" +
                "          \"purchasedDate\":\"25/07/2024\",\n" +
                "          \"count\":10\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":2,\n" +
                "      \"name\":\"Anna\",\n" +
                "      \"birthDate\":\"15/01/2000\",\n" +
                "      \"orders\":[\n" +
                "        {\n" +
                "          \"id\":4,\n" +
                "          \"name\":\"Juice\",\n" +
                "          \"purchasedDate\":\"26/07/2024\",\n" +
                "          \"count\":4\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":5,\n" +
                "          \"name\":\"Water\",\n" +
                "          \"purchasedDate\":\"26/07/2024\",\n" +
                "          \"count\":5\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":3,\n" +
                "      \"name\":\"Antonio\",\n" +
                "      \"birthDate\":\"22/06/1998\",\n" +
                "      \"orders\":[\n" +
                "        {\n" +
                "          \"id\":6,\n" +
                "          \"name\":\"Banana\",\n" +
                "          \"purchasedDate\":\"27/07/2024\",\n" +
                "          \"count\":5\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try (
                CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(url);
            postRequest.setHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(jsonBody);
            postRequest.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                if (response.getCode() == 200) {
                    System.out.println("200");
                } else if (response.getCode() == 500) {
                    System.out.println("500");
                }
            }
        } catch (
                Exception exception) {
            System.err.println("err " + exception.getMessage());
        }
    }
}
