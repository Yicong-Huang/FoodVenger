package pers.yicong.foodvenger.filter;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class VerifyRecaptcha {

    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET_KEY = "6LcR90cUAAAAAEGpt_Q3GUffwlOzfyoInFNyzk69"; // private key
    public static final String SITE_KEY = "6LcR90cUAAAAAJno_lgI8_hFO3CAvLSIJ9KGA6ff";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
            conn.setDoOutput(true);

            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode=" + responseCode);

            InputStream is = conn.getInputStream();
            String jsonstr = IOUtils.toString(is, "UTF-8");
            JSONObject json = new JSONObject(jsonstr);

            // {"success": true}
            System.out.println("Response: " + json.toString());

            return json.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}