package org.sushisushi.sushiform.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.sushisushi.sushiform.ApiService;
import org.sushisushi.sushiform.models.User;

import java.io.IOException;


@Controller
@RequestMapping("signup")
public class FormController {

    @RequestMapping(value="")
    public String index() throws IOException {
        System.out.println(ApiService.getApiKey());
        return "form/index"; //html file name
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    @ResponseBody
    public User newCustomer(@RequestBody User user){ //request param must match html

       return new User("NEW123", "NEW SMITH");
    }

}
