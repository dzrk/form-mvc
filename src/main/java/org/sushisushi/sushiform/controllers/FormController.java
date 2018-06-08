package org.sushisushi.sushiform.controllers;

import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.sushisushi.sushiform.ApiService;
import org.sushisushi.sushiform.reCaptcha.CaptchaService;
import org.sushisushi.sushiform.reCaptcha.RecaptchaUtil;

import java.io.IOException;


@Controller
@RequestMapping("signup")
public class FormController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String index(Model model) throws IOException, JSONException {
        //System.out.println(ApiService.getApiData("Authorisation?").getString("ApiKey"));
        return "form/index"; //html file name
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String processResponse(WebRequest request, Model model) throws IOException, JSONException { //request param must match html

        String response = request.getParameter("g-recaptcha-response");
        ResponseBody responseBody = CaptchaService.processResponse(response);
        JSONObject responseObj = new JSONObject(responseBody.string());
        System.out.println(responseObj);
        if((Boolean)responseObj.get("success")) {
            JSONObject payload = generatePayload(request);
            model.addAttribute("resCode", ApiService.postToApi("Member", payload));
        }else{
            String errMsg = getErrorMsg(responseObj);
            model.addAttribute("errorCode","Captcha failed, please try again");
        }
        return "form/index";
    }

    public JSONObject generatePayload(WebRequest request){
        JSONObject payload = new JSONObject();
        JSONArray jArray = new JSONArray();
        payload.put("FirstName",request.getParameter("FirstName"));
        payload.put("Surname",request.getParameter("Surname"));
        payload.put("EmailAddress",request.getParameter("EmailAddress"));
        payload.put("MobilePhone",request.getParameter("MobilePhone"));
        JSONObject jObject = new JSONObject();
        jObject.put("Id",2);
        jArray.put(jObject);
        payload.put("Classifications", jArray);
        jArray = new JSONArray();
        jObject = new JSONObject();
        jObject.put("Postcode",request.getParameter("Postcode"));
        jArray.put(jObject);
        payload.put("MailingAddress",jArray);
        return payload;
    }
    public String getErrorMsg(JSONObject responseObj){
                
        JSONArray errs = responseObj.getJSONArray("error-codes");
        if (errs.length()>0) {
            return RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(errs.getString(0));
        }else{
            return "Internal server error 500"; // Should never get here.. but just in case
        }
    }

}
