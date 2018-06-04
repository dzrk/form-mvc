package org.sushisushi.sushiform.controllers;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.sushisushi.sushiform.ApiService;
import org.sushisushi.sushiform.models.User;

import java.io.IOException;


@Controller
@RequestMapping("signup")
public class FormController {

    @RequestMapping(value="")
    public String index() throws IOException, JSONException {
        System.out.println(ApiService.getApiData("Authorisation?").getString("ApiKey"));
        return "form/index"; //html file name
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public ModelAndView method(WebRequest request){ //request param must match html
        System.out.println(request.getParameter("FirstName"));
        return new ModelAndView("Redirect:" + "http://sushi.dstgroup.com.au/");
    }

}
