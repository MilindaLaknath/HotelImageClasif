import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;

public class JavaJsonSmpl {
    public static void main(String[] args) {
        try {
            String url = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyCu73vgmAbTB6DBdlfsBpXYd8VQ1LDbXPI";
            String payload = "{\n" +
                    "  \"requests\": [\n" +
                    "    {\n" +
                    "      \"features\": [\n" +
                    "        {\n" +
                    "          \"type\": \"LABEL_DETECTION\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"type\": \"LANDMARK_DETECTION\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"type\": \"FACE_DETECTION\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"type\": \"TEXT_DETECTION\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"type\": \"SAFE_SEARCH_DETECTION\"\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"image\": {\n" +
                    "        \"source\": {\n" +
                    "          \"imageUri\": \"https://storage.googleapis.com/ruslimgbkt2/CASA1.jpg\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            StringEntity entity = new StringEntity(payload,ContentType.APPLICATION_JSON);//APPLICATION_FORM_URLENCODED);

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            InputStream is = response.getEntity().getContent();

            String responceText = convertStreamToString(is);

            System.out.println(responceText);

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(responceText);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            JSONArray resp = (JSONArray) jsonObject.get("responses");
            Iterator<JSONObject> iterator = resp.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
