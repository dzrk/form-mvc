package org.sushisushi.sushiform.reCaptcha;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.sushisushi.sushiform.ApiService;
import org.sushisushi.sushiform.models.CaptchaSettings;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

public class CaptchaService {
    @Autowired
    private static CaptchaSettings captchaSettings;

    @Autowired
    private RestOperations restTemplate;
    @Autowired
    private HttpServletRequest request;

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    public static void processResponse(String response, Model model) throws IOException {

        final String verifyUri = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", getReCaptchaSecret(), response);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(verifyUri)
                .build();
        Response googleResponse = client.newCall(request).execute();
        if (!googleResponse.isSuccessful()) {
            model.asMap().put("resCode",401);
        }else{
            model.asMap().put("resCode",201);
        }
    }


    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

    public String getReCaptchaSite() {
        return captchaSettings.getSite();
    }


    public static String getReCaptchaSecret() {
        return captchaSettings.getSecret();
    }

}
