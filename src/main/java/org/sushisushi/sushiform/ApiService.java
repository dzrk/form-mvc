package org.sushisushi.sushiform;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public String postToApi(String route, HashMap<String,String> formParams) throws IOException {

        OkHttpClient client = new OkHttpClient();

        MultipartBody.Builder newBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            newBuilder.addFormDataPart(entry.getKey(), entry.getValue());
        }

        MultipartBody requestBody = newBuilder.build();

        Request request = new Request.Builder()
                .url(BASE_URL + route)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}