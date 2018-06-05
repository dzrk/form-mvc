package org.sushisushi.sushiform;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class ApiService{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static String BASE_URL = config.getBaseUrl();

    public static JSONObject getApiData(String route) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        String auth = config.getAuth();
        String url = "";
        if (route.equals("Authorisation?")){
            url = BASE_URL +
                    route + auth;
        }else{
            url = BASE_URL + route;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonData = response.body().string();
        return new JSONObject(jsonData);
    }

    public static String postToApi(String route, JSONObject payload) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        String json = payload.toString();
        String url = BASE_URL + route;
        RequestBody body = RequestBody.create(JSON, json);
        System.out.println(payload.toString());
        Request request = new Request.Builder()
                .url(url)
                .header("ApiKey",ApiService.getApiData("Authorisation?").getString("ApiKey"))
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().string();

    }

}