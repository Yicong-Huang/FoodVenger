import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class YelpExtractor {
    public static void main(String[] args) {
        URL url = null;
        try {

            HttpURLConnection con;
            for (int i = 0; i < 20;i++){
                url = new URL("https://api.yelp.com/v3/businesses/search?term=restaurants&location=USA&limit=50&offset="+i*50);

                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(2000);
                con.setReadTimeout(2000);
                con.setRequestProperty("Authorization", "Bearer M864mCG359JqoTOj38ejlEA5-HAlsXTOPoSj9kN0XUILwdl_ldd8cBdXpwK2hBi6U6kulwEf3NIPEYQZ3p46r3rdIaSBJfVZFCwTpbdFdGZlzSNR5i8IKe2wiqNnWnYx");



                int status = con.getResponseCode();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                BufferedWriter writer = new BufferedWriter(new FileWriter("data/page" + i+ ".txt"));

                while ((inputLine = in.readLine()) != null) {
                    writer.write(inputLine);
                }

                writer.close();
                con.disconnect();

            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


