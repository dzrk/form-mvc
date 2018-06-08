package org.sushisushi.sushiform;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.sushisushi.sushiform.models.Url;

import java.io.IOException;


public class ApiService{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static JSONObject getApiData(String route) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        String url;
        if (route.equals("Authorisation?")){
            url = getBaseUrl() +
                    route + getAuthUrl();
        }else{
            url = getBaseUrl() + route;
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

    public static int postToApi(String route, JSONObject payload) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        String json = payload.toString();
        String url = getBaseUrl() + route;
        RequestBody body = RequestBody.create(JSON, json);
        System.out.println(payload.toString());
        Request request = new Request.Builder()
                .url(url)
                .header("ApiKey",ApiService.getApiData("Authorisation?").getString("ApiKey"))
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.code();
    }

    public static String getBaseUrl(){
        return Url.getBase();
    }

    public static String getAuthUrl(){
        return Url.getAuth();
    }

}