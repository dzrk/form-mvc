package org.sushisushi.sushiform;

import okhttp3.*;
import org.sushisushi.sushiform.config;

import java.io.IOException;
import java.util.Properties;

public class ApiService{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static String BASE_URL = "http://10.8.8.49:8081/WebApi/api/";
    public static String getApiKey() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String auth = config.getAuth();
        String url = BASE_URL +
                "Authorisation?" + auth;

        Request request = new Request.Builder()
        .url(url)
        .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postToApi(String route) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("somParam", "someValue")
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + route)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}