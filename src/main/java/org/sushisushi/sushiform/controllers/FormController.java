package org.sushisushi.sushiform.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.sushisushi.sushiform.ApiService;
import org.sushisushi.sushiform.reCaptcha.CaptchaService;

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
        CaptchaService.processResponse(response,model);
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
        model.addAttribute("resCode",ApiService.postToApi("Member", payload));
        return "form/index";
    }


}
