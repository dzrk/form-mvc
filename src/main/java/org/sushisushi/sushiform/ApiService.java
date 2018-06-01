package org.sushisushi.sushiform;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.sushisushi.sushiform.config;

import java.io.IOException;
import java.util.Properties;

public class ApiService{

    public static String getApiKey() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String auth = config.getAuth();
        String url = "http://10.8.8.49:8081/WebApi/api/" +
                "Authorisation?" + auth;

        Request request = new Request.Builder()
        .url(url)
        .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}