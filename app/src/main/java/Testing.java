import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testing {
    private static final String API_KEY = "AIzaSyARl1eB1xlwwfu0bkG2sUiO7rRtB0Dh36o";

    public static void main(String[] args) {
    }

    public static int calcTime(String origin, String dest) {
        try {
            String urlStr = "https://maps.googleapis.com/maps/api/distancematrix/json?"
                    + "origins=" + origin.replace(" ", "+")
                    + "&destinations=" + dest.replace(" ", "+")
                    + "&mode=driving"
                    + "&key=" + API_KEY;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read API response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


                  // Use regex to safely extract the value
                  String jsonResponse = response.toString();
            Pattern pattern = Pattern.compile("\"duration\"\\s*:\\s*\\{[^}]*\"value\"\\s*:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(jsonResponse);

                 if (matcher.find()) {
                    String durationValueStr = matcher.group(1); // The number in seconds, e.g. "5708"
                        int durationSeconds = Integer.parseInt(durationValueStr);
                        return durationSeconds;
                    } else {
                      System.out.println("Could not find travel time value in response.");
                    }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
