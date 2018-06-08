package org.sushisushi.sushiform.reCaptcha;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.sushisushi.sushiform.models.CaptchaSettings;

import java.io.IOException;
import java.util.regex.Pattern;

@Service
public class CaptchaService {
    @Autowired
    private static CaptchaSettings captchaSettings;

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    public static ResponseBody processResponse(String response) throws IOException {

        final String verifyUri = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s",
                getReCaptchaSecret(), response);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(verifyUri)
                .build();

        Response googleResponse = client.newCall(request).execute();

        return googleResponse.body();
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
