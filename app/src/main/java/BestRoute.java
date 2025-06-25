import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class BestRoute {
    public static String cth = "2/38-70 Gartside St, Wanniassa, ACT, Australia";
    public static String computeRoutes(String[] addresses){
        try{
            // API Endpoint
            URL url = new URL("https://routes.googleapis.com/directions/v2:computeRoutes");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // connection setup
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("X-Goog-Api-Key", "AIzaSyARl1eB1xlwwfu0bkG2sUiO7rRtB0Dh36o"); // Replace with your real API key
            conn.setRequestProperty("X-Goog-FieldMask", "routes,geocodingResults.intermediates.intermediateWaypointRequestIndex");
            conn.setDoOutput(true);

            // Build the JSON request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("origin", new JSONObject().put("address", cth ));
            requestBody.put("destination", new JSONObject().put("address", cth));

            // Add waypoints
            JSONArray intermediates = new JSONArray();
            for (String address : addresses) {
                intermediates.put(new JSONObject().put("address", address));
            }
            requestBody.put("intermediates", intermediates);
            requestBody.put("travelMode", "DRIVE");
            requestBody.put("optimizeWaypointOrder", "true");

            OutputStream os = conn.getOutputStream();
            os.write(requestBody.toString().getBytes());
            os.flush();
            os.close();

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
